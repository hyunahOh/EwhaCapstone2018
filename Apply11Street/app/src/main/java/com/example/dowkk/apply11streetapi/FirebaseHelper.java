package com.example.dowkk.apply11streetapi;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseHelper {
    private ArrayList<Product> products;
    private DatabaseReference db;

    private String userId; // I don't know what this is for,,

    private static FirebaseHelper firebaseHelper;

    private FirebaseHelper() {
        db = FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseHelper getInstance() {
        if(firebaseHelper == null) {
            firebaseHelper = new FirebaseHelper();
            return firebaseHelper;
        }else {
            return firebaseHelper;
        }
    }
    public Boolean saveProduct(Product product){
        Boolean saved;
        if(product == null){
            saved = false;
        } else {
            try {
                DatabaseReference userRef = db.child("Users").child(userId);
                String productKey = userRef.child("Products").push().getKey();
                product.setId(productKey);
                userRef.child("Products").child(productKey).setValue(product);
                saved = true;
            }catch (DatabaseException e){
                e.printStackTrace();
                saved = false;
            }

        }
        return saved;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
