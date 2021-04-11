package com.ch.nudge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class RegisterActivity_1 extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Button next;

    private FirebaseAuth auth;
    private DatabaseReference ref;
    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_1);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.passwordConfirm);
        next = findViewById(R.id.next);

        auth = FirebaseAuth.getInstance();

        ref = FirebaseDatabase.getInstance().getReference();
        userRef = ref.child("users");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = email.getText().toString().trim();
                String textPassword = password.getText().toString().trim();
                String textPasswordConfirm = confirmPassword.getText().toString().trim();

                if(TextUtils.isEmpty(textEmail) || TextUtils.isEmpty(textPassword)) {
                    Toast.makeText(RegisterActivity_1.this, "Missing email or password!", Toast.LENGTH_SHORT).show();
                }else if(textPassword.length() < 6) {
                    Toast.makeText(RegisterActivity_1.this, "Password too short!", Toast.LENGTH_SHORT).show();
                }else if(!textPassword.equals(textPasswordConfirm)) {
                    Toast.makeText(RegisterActivity_1.this, "Passwords must be matching!", Toast.LENGTH_SHORT).show();
                }else {
                    registerUser(textEmail, textPassword);
                }
            }
        });
    }

    private void registerUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity_1.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    User user = new User(email);
                    userRef.child(email.replaceAll("\\p{Punct}", "")).setValue(user);
                    startActivity(new Intent(RegisterActivity_1.this, RegisterActivity_2.class).putExtra("User", (Serializable) user));
                    finish();
                }else {
                    Toast.makeText(RegisterActivity_1.this, "Oops! Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}