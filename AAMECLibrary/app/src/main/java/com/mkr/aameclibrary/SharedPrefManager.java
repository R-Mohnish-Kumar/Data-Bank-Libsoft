package com.mkr.aameclibrary;

import android.content.ContentProvider;
import android.content.Context;
import android.content.SharedPreferences;

import com.mkr.aameclibrary.Staff.ui.StaffDetails;
import com.mkr.aameclibrary.Student.ui.StudentDetails;

public class SharedPrefManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    String SHARED_PREF_NAME="itlibrary";

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUser(StudentDetails studentDetails) {
        sharedPreferences= context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("DOB",studentDetails.getDOB());
        editor.putString("Department",studentDetails.getDepartment());
        editor.putString("Name",studentDetails.getName());
        editor.putLong("Regno",studentDetails.getRegno());
        editor.putString("intern",studentDetails.getIntern());
        editor.putBoolean("logged",true);
        editor.apply();
    }
    public void saveStaffUser(StaffDetails staffDetails) {
        sharedPreferences= context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("DOB",staffDetails.getDOB());
        editor.putString("Name",staffDetails.getName());
        editor.putString("Email",staffDetails.getEmail());
        editor.putString("Designation",staffDetails.getDesignation());
        editor.putLong("staffId",staffDetails.getStaffId());
        editor.putBoolean("logged",true);
        editor.apply();
    }
    public StaffDetails staffDetails(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new StaffDetails(sharedPreferences.getString("DOB",null),
                sharedPreferences.getString("Name",null),
                sharedPreferences.getString("Email",null),
                sharedPreferences.getLong("staffId",0),
                sharedPreferences.getString("Designation",null));
    }

    public StudentDetails details(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new StudentDetails(sharedPreferences.getString("DOB",null),
                sharedPreferences.getString("Department",null),
                sharedPreferences.getString("Name",null),
                sharedPreferences.getLong("Regno",0),
                sharedPreferences.getString("intern",null));
    }
    public void logout(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public boolean isStaffLoggedIn(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);
    }
    public boolean isStudentLoggedIn(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);
    }
}
