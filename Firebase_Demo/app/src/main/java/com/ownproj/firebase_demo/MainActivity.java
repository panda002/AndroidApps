package com.ownproj.firebase_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.provider.DocumentsProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity implements LoginFragment.LoginFragmentInterface, NewAccountFragment.SignupFragmentInterface {

    private static final String NAME_KEY = "";

    //private DocumentReference documentReference = FirebaseFirestore.getInstance().document("sampledata/names");
    //private Button button;
    //private EditText editText;

    public static FirebaseAuth firebaseAuth;
    public static DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference childRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button = findViewById(R.id.button);
        //editText = findViewById(R.id.editText);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    String userId = user.getUid();

                    childRef = rootRef.child(userId);
                    childRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                        });
                }
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.container, new LoginFragment(), "login_fragment").commit();
        }

        /*final String name = editText.getText().toString();
        button.setOnClickListener(new View.OnClickLis



        tener() {
            @Override
            public void onClick(View view) {
                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("first", "Ada");
                user.put("last", "Lovelace");
                user.put("born", 1815);

                    // Add a new document with a generated ID
                documentReference.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("Success", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Failure", "Error adding document", e);
                            }
                        });

            }
        });

    }*/


    @Override
    public void goToCreateAccount() {

    }

    @Override
    public void signIn(String email, String password) {

    }

    @Override
    public void goToLogin() {

    }

    @Override
    public void signUp(String fullName, String email, String password) {

    }
}
