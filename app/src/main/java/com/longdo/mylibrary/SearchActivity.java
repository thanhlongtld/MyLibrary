package com.longdo.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private EditText searchField;
    private RecyclerView booksRecView;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initComponents();

        Intent intent = getIntent();
        if (null != intent) {
            String classify = intent.getStringExtra("classify");
            if (!"".equals(classify)) {
                handleSearch(classify);
            }
        }
    }

    private void initComponents() {
        searchField = findViewById(R.id.searchField);
        booksRecView = findViewById(R.id.booksRecView);
        adapter = new BookRecViewAdapter(this, "allBooks");
        booksRecView.setAdapter(adapter);
        booksRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void handleSearch(final String classify) {

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String currentSearch = s.toString();
                if ("".equals(currentSearch)){
                    adapter.setBooks(new ArrayList<Book>());
                    return;
                }
                if ("allBooks".equals(classify)) {
                    ArrayList<Book> result = Utils.getInstance(SearchActivity.this).getFromAllBooksByName(currentSearch);
                    adapter.setBooks(result);
                }
                if ("alreadyRead".equals(classify)){
                    ArrayList<Book> result = Utils.getInstance(SearchActivity.this).getFromAlreadyReadBooksByName(currentSearch);
                    adapter.setBooks(result);
                }
                if ("currentlyReading".equals(classify)){
                    ArrayList<Book> result = Utils.getInstance(SearchActivity.this).getFromCurrentlyReadingBooksByName(currentSearch);
                    adapter.setBooks(result);
                }
                if ("favorite".equals(classify)){
                    ArrayList<Book> result = Utils.getInstance(SearchActivity.this).getFromFavoriteBooksByName(currentSearch);
                    adapter.setBooks(result);
                }
                if ("wantToRead".equals(classify)){
                    ArrayList<Book> result = Utils.getInstance(SearchActivity.this).getFromWantToReadBooksByName(currentSearch);
                    adapter.setBooks(result);
                }
            }
        });
    }
}