package com.example.teamsplash.donationtracker.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

public abstract class FirebaseLoginTask implements LoginTask {
    public FirebaseLoginTask(final String userEmailAddress, final String userPassword) {
        final FirebaseFirestore database = FirebaseFirestore.getInstance();
        final DocumentReference userEmailAddressDocumentReference = database.collection("users").document(userEmailAddress);
        database.runTransaction(new Transaction.Function<User>() {
            @Nullable
            @Override
            public User apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                final DocumentSnapshot snapshot = transaction.get(userEmailAddressDocumentReference);

                if (!(snapshot.exists()))
                    throw new FirebaseFirestoreException("Email address not registered", FirebaseFirestoreException.Code.NOT_FOUND);

                final Pair pair = snapshot.toObject(Pair.class);

                if (!(userPassword.equals(pair.getUserPassword())))
                    throw new FirebaseFirestoreException("Password incorrect", FirebaseFirestoreException.Code.UNAUTHENTICATED);

                return pair.getUser();
            }
        }).addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                FirebaseLoginTask.this.onSuccess(user);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                FirebaseLoginTask.this.onFailure(e);
            }
        });
    }
}
