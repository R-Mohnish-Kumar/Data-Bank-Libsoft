package com.mkr.aameclibrary.Student.ui.student_books_issued;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mkr.aameclibrary.Admin.ui.books.BooksRecyclerAdapter;
import com.mkr.aameclibrary.Admin.ui.books.IssuedBookDetails;
import com.mkr.aameclibrary.BookDetails;
import com.mkr.aameclibrary.R;
import com.mkr.aameclibrary.SharedPrefManager;
import com.mkr.aameclibrary.Staff.ui.staff_book_issued.StaffBookIssuedRecyclerAdapter;
import com.mkr.aameclibrary.Student.StudentHomeActivity;
import com.mkr.aameclibrary.Student.ui.StudentDetails;
import com.mkr.aameclibrary.databinding.FragmentStudentBooksIssuedBinding;

import java.util.ArrayList;

public class StudentBooksIssuedFragment extends Fragment {
    RecyclerView booksRecycler;
    LinearLayoutManager linearLayoutManager;
    DatabaseReference databaseReference;
    SharedPrefManager sharedPrefManager;
    ProgressBar progressBar;
    ArrayList<IssuedBookDetails> bookDetailsArrayList;

    private FragmentStudentBooksIssuedBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentStudentBooksIssuedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        booksRecycler=root.findViewById(R.id.studentBookIssuedRecyclerView);
        booksRecycler.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(getContext());
        progressBar=root.findViewById(R.id.progressBar);
        booksRecycler.setLayoutManager(linearLayoutManager);
        progressBar.setVisibility(View.VISIBLE);

        StudentBookIssuedRecyclerAdapter bookIssuedRecyclerAdapter= new StudentBookIssuedRecyclerAdapter(bookDetailsArrayList,getContext());
        booksRecycler.setAdapter(bookIssuedRecyclerAdapter);
        sharedPrefManager=new SharedPrefManager(getActivity());
        bookDetailsArrayList=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("BookIssued");
        StudentBookIssuedRecyclerAdapter booksRecyclerAdapter=new StudentBookIssuedRecyclerAdapter(bookDetailsArrayList,getContext());
        booksRecycler.setAdapter(booksRecyclerAdapter);
        Query checkUser=databaseReference.orderByChild("issuerName").equalTo(sharedPrefManager.details().getName());
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
        binding = null;
    }
}