package com.example.work_exchange;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupTabFragment extends Fragment {

    private TextInputLayout regUsername,regEmail,regPassword,regPhone;
    private Button btnReg;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;
    private String userID;
    private FirebaseFirestore firebaseFirestore;
    private float v=0;
    public static final String TAG="名字";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signup_wallpaper, container, false);

        regUsername=root.findViewById(R.id.textInputLayoutUsername);
        regEmail=root.findViewById(R.id.textInputLayoutEmail);
        regPassword=root.findViewById(R.id.textInputLayoutPassword);
        regPhone=root.findViewById(R.id.textInputLayoutPhone);
        btnReg=root.findViewById(R.id.buttonRegister);
       // progressBar=root.findViewById(R.id.progressbar);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getContext(),MainActivity.class));
            getActivity().onBackPressed();
        }

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=regUsername.getEditText().getText().toString().trim();
                String phone=regPhone.getEditText().getText().toString().trim();
                String email=regEmail.getEditText().getText().toString().trim();
                String password=regPassword.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    regEmail.setError("需要填寫E-mail");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    regPassword.setError("需要填寫密碼");
                    return;
                }

                if(password.length()<6){
                    regPassword.setError("密碼需要大於6位數");
                    return;
                }

               // progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser fuser = firebaseAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                }
                            });
                            Toast.makeText(getContext(), "User Created.", Toast.LENGTH_SHORT).show();
                            userID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firebaseFirestore.collection("Trade User").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Name",Name);
                            user.put("email",email);
                            user.put("phone",phone);
                            user.put("password",password);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                   Log.d(TAG, "註冊失敗: " + e.toString());
                                }
                            });
                           startActivity(new Intent(getContext(), MainActivity.class));

                        }
                        else {
                            Toast.makeText(getContext(),"註冊失敗!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                           // progressBar.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });

        /*
        regUsername=root.findViewById(R.id.textInputLayoutUsername);
        regPhone=root.findViewById(R.id.textInputLayoutPhone);
        regEmail=root.findViewById(R.id.textInputLayoutEmail);
        regPassword=root.findViewById(R.id.textInputLayoutPassword);
        btnReg=root.findViewById(R.id.buttonRegister);

        regUsername.setTranslationY(800);
        regPhone.setTranslationY(800);
        regEmail.setTranslationY(800);
        regPassword.setTranslationY(800);
        btnReg.setTranslationY(800);

        regUsername.setAlpha(v);
        regPhone.setAlpha(v);
        regEmail.setAlpha(v);
        regPassword.setAlpha(v);
        btnReg.setAlpha(v);

        regUsername.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(100).start();
        regPhone.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        regEmail.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        regPassword.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();
        btnReg.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(900).start();*/

        return root;
    }

}
