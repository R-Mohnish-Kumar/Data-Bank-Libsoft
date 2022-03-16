package com.mkr.aameclibrary.Staff;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mkr.aameclibrary.Admin.AdminHomeActivity;
import com.mkr.aameclibrary.R;
import com.mkr.aameclibrary.SharedPrefManager;
import com.mkr.aameclibrary.Staff.ui.StaffDetails;
import com.mkr.aameclibrary.Student.StudentHomeActivity;
import com.mkr.aameclibrary.Student.ui.StudentDetails;

public class StaffFragment extends Fragment {
    EditText staffUsername;
    EditText staffPassword;
    Button loginButton;
    ProgressBar progressBar;
    SharedPrefManager sharedPrefManager;
    DatabaseReference reference;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_staff, container, false);
        staffUsername=view.findViewById(R.id.staffUserNameEditText);
        staffPassword=view.findViewById(R.id.staffPasswordEditText);
        loginButton=view.findViewById(R.id.staffLoginButton);
        progressBar=view.findViewById(R.id.progressBar);
        sharedPrefManager=new SharedPrefManager(getActivity());
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staffUsername.getText().toString().matches("")){
                    staffUsername.setError("Enter the staff id");
                    staffUsername.requestFocus();
                }
                else if (staffPassword.getText().toString().matches("")){
                    staffPassword.setError("Enter the date of birth");
                    staffPassword.requestFocus();
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    reference= FirebaseDatabase.getInstance().getReference().child("StaffDetails");
                    Query checkUser=reference.orderByChild("staffId").equalTo(Long.parseLong(staffUsername.getText().toString()));
                    checkUser.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot snapshot1: snapshot.getChildren()){
                                String password=snapshot1.child("DOB").getValue().toString();
                                if (password.equals(staffPassword.getText().toString())){
                                    StaffDetails staffDetails=snapshot1.getValue(StaffDetails.class);
                                    Log.e("name",staffDetails.getName());
                                    sharedPrefManager.saveStaffUser(staffDetails);
                                    Log.e("Shared pref",sharedPrefManager.details().getName());
                                    startActivity(new Intent(getActivity(), StaffHomeActivity.class));
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