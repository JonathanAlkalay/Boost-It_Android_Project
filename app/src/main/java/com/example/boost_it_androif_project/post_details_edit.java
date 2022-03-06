package com.example.boost_it_androif_project;

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

import com.example.boost_it_androif_project.model.Model;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class post_details_edit extends Fragment {

    private PostDetailsEditViewModel mViewModel;
    View view;
    Button confirm;
    Button delete;
    TextView companyName;
    TextView title;
    TextView hours;
    TextView price;
    TextView description;
    ImageView image;
    ImageButton camera;
    ImageButton gallery;


    public static post_details_edit newInstance() {
        return new post_details_edit();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.view =  inflater.inflate(R.layout.post_details_edit, container, false);

        if (mViewModel.getPost() == null){
            Model.instance.getPostById(post_details_editArgs.fromBundle(getArguments()).getPostId(), post -> {

                mViewModel.setPost(post);
                setScreen();
            });
        }else {
            setScreen();
        }
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mViewModel = new ViewModelProvider(this).get(PostDetailsEditViewModel.class);
    }

    private void setScreen() {

        confirm = view.findViewById(R.id.post_details_edit_confirmBttn);
        companyName = view.findViewById(R.id.post_details_edit_businessName);
        title = view.findViewById(R.id.post_details_edit_title);
        hours = view.findViewById(R.id.post_details_edit_hours);
        price = view.findViewById(R.id.post_details_edit_price);
        description = view.findViewById(R.id.post_details_edit_description);
        image = view.findViewById(R.id.post_details_edit_image);
        camera = view.findViewById(R.id.post_details_edit_camera_button);
        gallery = view.findViewById(R.id.post_details_edit_gallery_button);
        delete = view.findViewById(R.id.post_details_edit_delete_button);

        Model.instance.getBusinessByEmail(mViewModel.getPost().getBusinessEmail(), business_account
                -> companyName.setText(business_account.getCompanyName()));

        title.setText(mViewModel.getPost().getTitle());
        hours.setText(mViewModel.getPost().getTimes());
        price.setText(mViewModel.getPost().getPrice());
        description.setText(mViewModel.getPost().getDescription());
        Picasso.get()
                .load(mViewModel.getPost().getImage())
                .into(image);

        camera.setOnClickListener(v -> openCamera());
        gallery.setOnClickListener(v -> openGallery());

        //TODO complete, update post with timestamp in firebase and delete old post in DAO local db
        confirm.setOnClickListener(v -> {


            Navigation.findNavController(v).navigateUp();
        });

        //TODO complete, delete post with  in firebase and delete  post in DAO local db
        delete.setOnClickListener(v -> {


            Navigation.findNavController(v).popBackStack();
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
                image.setImageBitmap(imageBitmap);
            }
        } else if (requestCode == REQUEST_IMAGE_GALLERY){

                if (requestCode == Activity.RESULT_OK){
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContext().getContentResolver().openInputStream(imageUri);
                        imageBitmap = BitmapFactory.decodeStream(imageStream);
                        image.setImageBitmap(imageBitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
            }
    }
}