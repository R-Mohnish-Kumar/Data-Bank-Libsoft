package com.mkr.aameclibrary.Student;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mkr.aameclibrary.Admin.AdminHomeActivity;
import com.mkr.aameclibrary.R;
import com.mkr.aameclibrary.SharedPrefManager;
import com.mkr.aameclibrary.Staff.StaffHomeActivity;
import com.mkr.aameclibrary.Student.ui.StudentDetails;

import java.util.Objects;

public class StudentFragment extends Fragment {
    EditText studentUsername;
    EditText studentPassword;
    Button loginButton;
    ProgressBar progressBar;
    SharedPrefManager sharedPrefManager;
    DatabaseReference reference;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_student, container, false);
        studentUsername=view.findViewById(R.id.studentUserNameEditText);
        studentPassword=view.findViewById(R.id.studentPasswordEditText);
        loginButton=view.findViewById(R.id.studentLoginButton);
        progressBar=view.findViewById(R.id.progressBar);
        sharedPrefManager=new SharedPrefManager(getActivity());
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (studentUsername.getText().toString().matches("")){
                    studentUsername.setError("Enter the register number");
                    studentUsername.requestFocus();
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    reference=FirebaseDatabase.getInstance().getReference().child("StudentDetails");
                    Query checkUser=reference.orderByChild("Regno").equalTo(Long.parseLong(studentUsername.getText().toString()));
                    checkUser.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot snapshot1: snapshot.getChildren()){
                                String password=snapshot1.child("DOB").getValue().toString();
                                if (password.equals(studentPassword.getText().toString())){
                                    StudentDetails studentDetails=snapshot1.getValue(StudentDetails.class);
                                    Log.e("name",studentDetails.getName());
                                    sharedPrefManager.saveUser(studentDetails);
                                    Log.e("Shared pref",sharedPrefManager.details().getName());
                                    startActivity(new Intent(getActivity(),StudentHomeActivity.class));
                                    progressBar.setVisibility(View.GONE);
                                }else {
                                    Toast.makeText(getContext(),"Wrong password",Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("Error",error.getMessage());
                        }
                    });
                }
            }
        });

        return view;
    }

}