package com.example.boost_it_androif_project.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelFireBase {

    FirebaseFirestore db = FirebaseFirestore.getInstance();



    public void addUser(User_Account user){

        Map<String, Object> json = user.toJson(user);

        db.collection(User_Account.collectionName)
                .document(user.getEmail())
                .set(json)
                .addOnSuccessListener(unused -> { })
                .addOnFailureListener(e -> { });
    }
//
//    public List<User_Account> getAllUsers(){
//
//        db.collection(User_Account.collectionName)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if(task.isSuccessful()){
//
//                        }
//                        else {
//
//                        }
//                    }
//                });
//    }


}
