package com.example.work_exchange;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginTabFragment extends Fragment{

    private TextInputLayout logEmail,logPassword;
    private Button btnLog;
    private FirebaseAuth firebaseAuth;
    private TextView forgotTextLink;
    private ProgressBar progressBar;
    private float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_log_in_wallpaper, container, false);

        logEmail=root.findViewById(R.id.textInputLayoutEmail);
        logPassword=root.findViewById(R.id.textInputLayoutPassword);
        btnLog=root.findViewById(R.id.buttonLogin);
        firebaseAuth=FirebaseAuth.getInstance();
        forgotTextLink = root.findViewById(R.id.forgotPassword);
        //progressBar=root.findViewById(R.id.progressbar);

        logEmail.setTranslationY(800);
        logPassword.setTranslationY(800);
        btnLog.setTranslationY(800);

        logEmail.setAlpha(v);
        logPassword.setAlpha(v);
        btnLog.setAlpha(v);

        logEmail.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        logPassword.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btnLog.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();



        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=logEmail.getEditText().getText().toString().trim();
                String password=logPassword.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    logEmail.setError("需要填寫E-mail");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    logPassword.setError("需要填寫密碼");
                    return;
                }

                if(password.length()<6){
                    logPassword.setError("密碼需要大於6位數");
                    return;
                }

               // progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(), MainActivity.class));
                        }else {
                            Toast.makeText(getContext(), "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           // progressBar.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });
        forgotTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext() ,"Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),"Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });

        return root;
    }

}
