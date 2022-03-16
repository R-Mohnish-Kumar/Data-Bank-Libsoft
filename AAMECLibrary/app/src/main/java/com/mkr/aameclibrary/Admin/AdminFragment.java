package com.mkr.aameclibrary.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mkr.aameclibrary.BookDetails;
import com.mkr.aameclibrary.R;
import com.mkr.aameclibrary.Student.StudentHomeActivity;

import java.util.ArrayList;

public class AdminFragment extends Fragment {
    EditText adminUsername;
    EditText adminPassword;
    TextView forgotPassword;
    Button loginButton;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_admin, container, false);
        adminUsername=view.findViewById(R.id.userNameEditText);
        adminPassword=view.findViewById(R.id.passwordEditText);
        forgotPassword=view.findViewById(R.id.forgotPasswordTextView);
        loginButton=view.findViewById(R.id.adminLoginButton);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        progressBar=view.findViewById(R.id.progressBar);
        if (firebaseUser!=null){
            login();
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (adminUsername.getText().toString().matches("")){
                    adminUsername.setError("Enter the username");
                    adminUsername.requestFocus();
                }
                else if (adminPassword.getText().toString().matches("")){
                    adminPassword.setError("Enter the password");
                    adminPassword.requestFocus();
                }
                else {
                    firebaseAuth.signInWithEmailAndPassword(adminUsername.getText().toString(),adminPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                login();
                                progressBar.setVisibility(View.GONE);
                            }else {
                                Toast.makeText(getContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });

                }

            }
        });
        return view;
    }
    void login(){
        Intent intent = new Intent(getActivity(), AdminHomeActivity.class);
        startActivity(intent);
        Toast.makeText(getContext(), "Signed In", Toast.LENGTH_SHORT).show();
    }
}