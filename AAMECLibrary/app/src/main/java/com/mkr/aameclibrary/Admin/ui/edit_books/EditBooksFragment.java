package com.mkr.aameclibrary.Admin.ui.edit_books;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mkr.aameclibrary.Admin.ui.books.BookIssuePopWindowActivity;
import com.mkr.aameclibrary.Admin.ui.books.BooksFragment;
import com.mkr.aameclibrary.Admin.ui.books.IssuedBookDetails;
import com.mkr.aameclibrary.R;
import com.mkr.aameclibrary.SharedPrefManager;
import com.mkr.aameclibrary.databinding.FragmentEditBooksBinding;

public class EditBooksFragment extends Fragment {
    TextInputEditText bookName,author,publisher;
    Button addNewButton;
    DatabaseReference databaseReference;
    SharedPrefManager sharedPrefManager;

    private FragmentEditBooksBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditBooksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sharedPrefManager=new SharedPrefManager(getActivity());
        bookName=root.findViewById(R.id.newBookNameEditText);
        author=root.findViewById(R.id.newBookAuthorEditText);
        publisher=root.findViewById(R.id.newBookPublisherEditText);
        addNewButton=root.findViewById(R.id.addNewBookButton);
        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewBookDetails addBookDetails=new AddNewBookDetails(bookName.getText().toString(),author.getText().toString(),publisher.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Books").push().setValue(addBookDetails);
                Toast.makeText(getContext(),"Book Added",Toast.LENGTH_SHORT).show();
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