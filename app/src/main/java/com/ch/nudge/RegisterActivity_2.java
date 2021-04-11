package com.ch.nudge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity_2 extends AppCompatActivity{

    private EditText phoneNumber;
    private EditText preference;
    private Button register;

    //private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_2);

        phoneNumber = findViewById(R.id.phone);
        preference = findViewById(R.id.preference);
        register = findViewById(R.id.register);

        //ref = FirebaseDatabase.getInstance().getReference();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textNumber = phoneNumber.getText().toString().trim();
                String textPreference = preference.getText().toString().trim();

                if(textNumber.length() != 10) {
                    Toast.makeText(RegisterActivity_2.this, "Invalid phone number!", Toast.LENGTH_SHORT).show();
                }else {
                    setPreferences(textNumber, textPreference);
                }
            }
        });
    }

    private void setPreferences(String number, String preference) {
        Toast.makeText(RegisterActivity_2.this, "Registration successful!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity_2.this, Home.class));
    }
}