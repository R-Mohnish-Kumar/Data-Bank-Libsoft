package com.mkr.aameclibrary.Student.ui.student_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mkr.aameclibrary.R;
import com.mkr.aameclibrary.SharedPrefManager;
import com.mkr.aameclibrary.databinding.FragmentStudentProfileBinding;

public class StudentProfileFragment extends Fragment {
    TextView regNo,name,dob;
    SharedPrefManager sharedPrefManager;

    private FragmentStudentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentStudentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sharedPrefManager=new SharedPrefManager(getContext());
        regNo=root.findViewById(R.id.studentRegisterNoTextView);
        name=root.findViewById(R.id.studentNameTextView);
        dob=root.findViewById(R.id.studentDobTextView);
        regNo.setText(String.valueOf(sharedPrefManager.details().getRegno()));
        name.setText(sharedPrefManager.details().getName());
        dob.setText(sharedPrefManager.details().getDOB());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}