package come.example.myone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignHome extends AppCompatActivity {
    EditText emailIDD, passwordIDD;
    TextView textSignUp;
    Button buttonLogin;
    FirebaseAuth mFirebaseAuth;
    ProgressBar progressBar;
    ImageView logoImage;
    Button guestLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_home);
        textSignUp = findViewById(R.id.SHReigster);
        emailIDD = findViewById(R.id.SHUserName);
        passwordIDD = findViewById(R.id.SHPassword);
        buttonLogin = findViewById(R.id.SHSignIn);
        mFirebaseAuth = FirebaseAuth.getInstance();
        guestLogin = findViewById(R.id.SHGuest);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailIDD.getText().toString();
                String pwd = passwordIDD.getText().toString();
                if (email.isEmpty()) {
                    emailIDD.setError("please enter an email");
                    emailIDD.requestFocus();
                } else if (pwd.isEmpty()) {
                    passwordIDD.setError("please enter a password");
                    passwordIDD.requestFocus();

                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "Login Complete", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignHome.this, HomeActivity.class);
                                intent.putExtra("userEmail",email);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "PLEASE TRY AGAIN", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignHome.this, SignUpActivity.class);
                startActivity(i);
            }


        });

        guestLogin.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              Intent intent = new Intent (SignHome.this,HomeActivity.class);
                                              intent.putExtra("userEmail","guest");
                                              startActivity(intent);
                                          }
                                      }
        );
    }

}


