package come.example.myone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    EditText emailadd, passwordd;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_account);
        emailadd = findViewById(R.id.SIUser);
        passwordd = findViewById(R.id.SIPass);
        btnSignUp = findViewById(R.id.SIConfirm);
        mFirebaseAuth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaild = emailadd.getText().toString();
                String pwdd = passwordd.getText().toString();

                if (emaild.isEmpty()) {
                    emailadd.setError("please enter an email");
                    emailadd.requestFocus();
                } else if (pwdd.isEmpty()) {
                    passwordd.setError("please enter a password");
                    passwordd.requestFocus();

                } else if (!(emaild.isEmpty() && pwdd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(emaild, pwdd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registration Complete", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, SignHome.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "PLEASE TRY AGAIN", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }
}
