package com.example.boost_it_androif_project.Business;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boost_it_androif_project.R;
import com.example.boost_it_androif_project.model.Model;
import com.example.boost_it_androif_project.model.post;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class business_post_new_add extends Fragment {

    private BusinessPostNewAddViewModel mViewModel;
    View view;
    ImageView picture;
    public static business_post_new_add newInstance() {
        return new business_post_new_add();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.business_post_new_add, container, false);

        ImageButton camera = view.findViewById(R.id.business_post_new_add_camera_button);
        ImageButton gallery = view.findViewById(R.id.business_post_new_add_gallery_button);
        picture = view.findViewById(R.id.business_post_new_add_image);

        camera.setOnClickListener(v->{
            openCamera();
        });
        gallery.setOnClickListener(v->{
            openGallery();
        });

        if (mViewModel.getAccount() == null) {
            Model.instance.getBusinessByEmail(business_post_new_addArgs.fromBundle(getArguments()).getBusinessEmail(),
                    business_account -> {
                        mViewModel.setAccount(business_account);

                        TextView compName = view.findViewById(R.id.business_post_new_add_businessName);
                        compName.setText(mViewModel.getAccount().getCompanyName());
                        setScreen();
                    });
        } else {
            TextView compName = view.findViewById(R.id.business_post_new_add_businessName);
            compName.setText(mViewModel.getAccount().getCompanyName());
            setScreen();
        }
        return this.view;
    }

    @Override
    public void onAttach (@NonNull Context context){
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(BusinessPostNewAddViewModel.class);
    }

    private void setScreen() {

        Button confirm = view.findViewById(R.id.business_post_new_add_enter_button);
        confirm.setOnClickListener(v -> {

            TextView title = view.findViewById(R.id.business_post_new_add_title);
            TextView hours = view.findViewById(R.id.business_post_new_add_hours);
            TextView price = view.findViewById(R.id.business_post_new_add_price);
            TextView description = view.findViewById(R.id.business_post_new_add_description);

            String ttle = title.getText().toString();
            String hrs = hours.getText().toString();
            String prce = price.getText().toString();
            String dscrptn = description.getText().toString();


            if (imageBitmap!= null && !ttle.equals("") && !hrs.equals("") && !prce.equals("") && !dscrptn.equals("")) {

                post post = new post();

                post.setBusinessEmail(mViewModel.getAccount().getEmail());
                post.setTitle(ttle);
                post.setPrice(prce);
                post.setTimes(hrs);
                post.setDescription(dscrptn);
                post.setKey(post.generateKey(mViewModel.getAccount().getEmail()));

                Toast toast = Toast.makeText(getActivity(), "Added Post", Toast.LENGTH_LONG);
                toast.show();

                Model.instance.saveImage(imageBitmap,post.getKey()+".jpg", url -> {
                    post.setImage(url);

                    Model.instance.addPost(post, () -> Navigation.findNavController(view).navigateUp());
                });

            } else {
                Toast toast = Toast.makeText(getActivity(), "missing fields", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_GALLERY = 2;
    private void openCamera(){

        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture,REQUEST_IMAGE_CAPTURE);
    }

    private void openGallery(){
        Intent galleryPicker = new Intent(Intent.ACTION_PICK);
        galleryPicker.setType("image/*");
        startActivityForResult(galleryPicker,REQUEST_IMAGE_GALLERY);
    }

    Bitmap imageBitmap = null;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == Activity.RESULT_OK) {

                Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");
                picture.setImageBitmap(imageBitmap);
            }
        } else if (requestCode == REQUEST_IMAGE_GALLERY){

                if (resultCode == Activity.RESULT_OK){
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);
                        imageBitmap = BitmapFactory.decodeStream(imageStream);
                        picture.setImageBitmap(imageBitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
            }
        }
}