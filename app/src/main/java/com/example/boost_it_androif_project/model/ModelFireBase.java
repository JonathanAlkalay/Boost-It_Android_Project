package com.example.boost_it_androif_project.model;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.firebase.Timestamp;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class ModelFireBase {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();


    public ModelFireBase() {
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false)
                .build();
        db.setFirestoreSettings(settings);
    }

    public void saveImage(Bitmap imageBit, String key, Model.saveImageListener listener) {

        StorageReference storageReference = storage.getReference();
        StorageReference imgRef = storageReference.child("post_image/" + key);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imageBit.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = imgRef.putBytes(data);
        uploadTask.addOnFailureListener(e -> {
            listener.onComplete(null);
        }).addOnSuccessListener(taskSnapshot -> {

            imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                Uri downloadUrl = uri;
                listener.onComplete(downloadUrl.toString());
            });
        });
    }


    public void addUser(User_Account user, Model.AddUserListener listener) {

        Map<String, Object> json = user.toJson(user);

        db.collection(User_Account.collectionName)
                .document(user.getEmail())
                .set(json)
                .addOnSuccessListener(unused -> listener.onComplete())
                .addOnFailureListener(e -> listener.onComplete());
    }

    public void getUserByEmail(String email, Model.GetUserByEmailListener listener) {

        db.collection(User_Account.collectionName)
                .document(email)
                .get()
                .addOnCompleteListener(task -> {
                    User_Account user = null;
                    if (task.isSuccessful() & task.getResult() != null) {
                        user = User_Account.UserfromJson(task.getResult().getData());
                    }
                    listener.onComplete(user);
                });
    }

    public void addBusiness(Business_Account business_account, Model.AddBusinessListener listener) {
        Map<String, Object> json = business_account.toJson(business_account);

        db.collection(Business_Account.collectionName)
                .document(business_account.getEmail())
                .set(json)
                .addOnSuccessListener(unused -> listener.onComplete())
                .addOnFailureListener(e -> listener.onComplete());
    }

    public void getBusinessByEmail(String email, Model.getBusinessByEmailListener listener) {
        db.collection(Business_Account.collectionName)
                .document(email)
                .get()
                .addOnCompleteListener(task -> {
                    Business_Account business_account = null;
                    if (task.isSuccessful() & task.getResult() != null) {
                        business_account = Business_Account.BusinessesfromJson(task.getResult().getData());
                    }
                    listener.onComplete(business_account);
                });
    }

    public void updateBusiness(String email, String field, Object value) {

        db.collection(Business_Account.collectionName)
                .document(email)
                .update(field, value);
    }

    public void addPost(post post, Model.AddPostListener listener) {

        Map<String, Object> json = post.toJson(post);

        db.collection(post.collectionName)
                .document(post.getKey())
                .set(json)
                .addOnSuccessListener(unused -> listener.onComplete())
                .addOnFailureListener(System.out::print);
    }


    public void deletePost(post post1, Model.deletePostListener listener) {

        new Thread(()->AppLocalDB.db.post_dao().delete(post1)).start();

        db.collection(post.collectionName).document(post1.getKey()).update("deleted",true).addOnSuccessListener(unused ->
                listener.onComplete());
    }

    public void getPostById(String id, Model.getPostByIdListener listener) {
        db.collection(post.collectionName)
                .document(id)
                .get()
                .addOnCompleteListener(task -> {
                    post post1 = null;
                    if (task.isSuccessful() & task.getResult() != null) {
                        post1 = post.postfromJson(task.getResult().getData());
                    }
                    listener.onComplete(post1);
                });
    }


    public interface getAllPostsListener {
        void onComplete(List<post> list);
    }

    public void getAllPosts(Long lastUpdateDate, getAllPostsListener listener) {
        db.collection(post.collectionName)
                .whereGreaterThanOrEqualTo("updateDate", new Timestamp(lastUpdateDate, 0))
                .get()
                .addOnCompleteListener(task -> {
                    List<post> list = new ArrayList<>();
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            post post1 = post.postfromJson(doc.getData());
                            if (post1 != null) {
                                list.add(post1);
                            }
                        }
                    }
                    for (post p:list){
                        if (p.getDeleted())
                            list.remove(p);
                    }

                    listener.onComplete(list);
                });
    }
}
