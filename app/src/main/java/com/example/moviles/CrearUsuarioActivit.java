package com.example.moviles;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CrearUsuarioActivit extends AppCompatActivity {
    TextInputEditText nuevoEmail, nuevaClave;
    Button crear;
    private FirebaseAuth mAuth;
    private String TAG = "CrearUsuarioActivit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        mAuth = FirebaseAuth.getInstance();
        nuevoEmail = findViewById(R.id.nuevoEmail);
        nuevaClave = findViewById(R.id.nuevaClave);
        crear = findViewById(R.id.crear);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearUsuario(nuevoEmail.getText().toString(), nuevaClave.getText().toString());
            }
        });
    }

    public void crearUsuario(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .

                        addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    finish();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(CrearUsuarioActivit.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
    }
}