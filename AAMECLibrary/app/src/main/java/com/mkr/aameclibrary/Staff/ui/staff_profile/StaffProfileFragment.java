package com.mkr.aameclibrary.Staff.ui.staff_profile;

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
import com.mkr.aameclibrary.databinding.FragmentStaffProfileBinding;

public class StaffProfileFragment extends Fragment {
    TextView staffId,name,email,dob,designation;
    SharedPrefManager sharedPrefManager;

    private FragmentStaffProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentStaffProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sharedPrefManager=new SharedPrefManager(getContext());
        staffId=root.findViewById(R.id.staffIdTextView);
        name=root.findViewById(R.id.staffNameTextView);
        email=root.findViewById(R.id.staffEmailTextView);
        dob=root.findViewById(R.id.staffDobTextView);
        designation=root.findViewById(R.id.staffDesignationTextView);

        staffId.setText(String.valueOf(sharedPrefManager.staffDetails().getStaffId()));
        name.setText(sharedPrefManager.staffDetails().getName());
        email.setText(sharedPrefManager.staffDetails().getEmail());
        dob.setText(sharedPrefManager.staffDetails().getDOB());
        designation.setText(sharedPrefManager.staffDetails().getDesignation());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}