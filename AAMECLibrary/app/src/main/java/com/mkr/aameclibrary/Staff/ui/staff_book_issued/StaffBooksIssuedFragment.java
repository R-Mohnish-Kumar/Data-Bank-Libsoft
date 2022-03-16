package com.mkr.aameclibrary.Staff.ui.staff_book_issued;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mkr.aameclibrary.Admin.ui.books.IssuedBookDetails;
import com.mkr.aameclibrary.R;
import com.mkr.aameclibrary.SharedPrefManager;
import com.mkr.aameclibrary.Student.ui.student_books_issued.StudentBookIssuedRecyclerAdapter;

import java.util.ArrayList;

public class StaffBooksIssuedFragment extends Fragment {
    RecyclerView booksRecycler;
    LinearLayoutManager linearLayoutManager;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
    SharedPrefManager sharedPrefManager;
    ArrayList<IssuedBookDetails> bookDetailsArrayList;
    View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root=inflater.inflate(R.layout.staff_books_issued_fragment, container, false);
        booksRecycler=root.findViewById(R.id.staffBookIssuedRecyclerView);
        booksRecycler.setHasFixedSize(true);
        progressBar=root.findViewById(R.id.progressBar);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        booksRecycler.setLayoutManager(linearLayoutManager);
        progressBar.setVisibility(View.VISIBLE);

        StaffBookIssuedRecyclerAdapter bookIssuedRecyclerAdapter= new StaffBookIssuedRecyclerAdapter(bookDetailsArrayList,getContext());
        booksRecycler.setAdapter(bookIssuedRecyclerAdapter);

        sharedPrefManager=new SharedPrefManager(getActivity());
        bookDetailsArrayList=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("BookIssued");
        StaffBookIssuedRecyclerAdapter booksRecyclerAdapter=new StaffBookIssuedRecyclerAdapter(bookDetailsArrayList,getContext());
        booksRecycler.setAdapter(booksRecyclerAdapter);
        Query checkUser=databaseReference.orderByChild("issuerName").equalTo(sharedPrefManager.staffDetails().getName());
        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    IssuedBookDetails issuedBookDetails= snapshot1.getValue(IssuedBookDetails.class);
                    bookDetailsArrayList.add(issuedBookDetails);
                }
                booksRecyclerAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error",error.getMessage());
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}