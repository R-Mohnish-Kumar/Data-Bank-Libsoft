package com.mkr.aameclibrary.Staff.ui.staff_book_holders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mkr.aameclibrary.Admin.ui.book_holders.BookHoldersRecyclerAdapter;
import com.mkr.aameclibrary.Admin.ui.books.IssuedBookDetails;
import com.mkr.aameclibrary.R;
import com.mkr.aameclibrary.databinding.FragmentBookHoldersBinding;
import com.mkr.aameclibrary.databinding.FragmentStaffBookHoldersBinding;

import java.util.ArrayList;

public class StaffBookHoldersFragment extends Fragment {
    RecyclerView booksRecycler;
    LinearLayoutManager linearLayoutManager;
    ArrayList<IssuedBookDetails> bookDetailsArrayList;
    DatabaseReference databaseReference;
    ProgressBar progressBar;

    private FragmentStaffBookHoldersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStaffBookHoldersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        booksRecycler=root.findViewById(R.id.staffBooksHolderRecyclerView);
        databaseReference= FirebaseDatabase.getInstance().getReference("BookIssued");
        booksRecycler.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        booksRecycler.setLayoutManager(linearLayoutManager);
        progressBar=root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        bookDetailsArrayList=new ArrayList<>();
        StaffBookHoldersRecyclerAdapter booksRecyclerAdapter=new StaffBookHoldersRecyclerAdapter(bookDetailsArrayList,getContext());
        booksRecycler.setAdapter(booksRecyclerAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    IssuedBookDetails bookHolderDetails=dataSnapshot.getValue(IssuedBookDetails.class);
                    bookDetailsArrayList.add(bookHolderDetails);
                }

                booksRecyclerAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Error",error.getDetails().toString());

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