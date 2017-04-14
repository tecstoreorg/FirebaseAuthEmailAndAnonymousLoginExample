package org.tec_store.firebaseauthemailandanonymousexample;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.google.android.gms.internal.zzt.TAG;



public class SignupActivity extends AppCompatActivity {
    EditText emailEt, passwordEt;
    Button signupBtn;
    FirebaseAuth mAuth;
    TextView signUpTextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        emailEt = (EditText) findViewById(R.id.input_email);
        passwordEt = (EditText) findViewById(R.id.input_password);
        signupBtn = (Button) findViewById(R.id.btn_signup);
        signUpTextview = (TextView) findViewById(R.id.signup_text_view);


        signUpTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupUser(emailEt.getText().toString(), passwordEt.getText().toString());
            }
        });

    }

    private void signupUser(final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "USER CREATED", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, task.getException().getMessage());
                    Toast.makeText(SignupActivity.this, "an error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
