package com.example.boost_it_androif_project.model;

import android.util.Log;
import android.view.Display;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelFireBase {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ModelFireBase(){
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(false)
                .build();
        db.setFirestoreSettings(settings);
    }


    public void addUser(User_Account user,Model.AddUserListener listener) {

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
                    if (task.isSuccessful() & task.getResult()!= null){
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
                    if (task.isSuccessful() & task.getResult()!= null){
                        business_account = Business_Account.BusinessesfromJson(task.getResult().getData());
                    }
                    listener.onComplete(business_account);
                });
    }
}
