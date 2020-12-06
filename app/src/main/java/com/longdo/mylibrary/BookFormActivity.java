package com.longdo.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BookFormActivity extends AppCompatActivity {
    private EditText bookNameField, authorField, pagesField, imgUrlField, shortDescField, longDescField, urlField;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);

        initComponents();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmitClick();
            }
        });
    }

    private void initComponents() {
        submitButton = findViewById(R.id.submitButton);
        bookNameField = findViewById(R.id.bookNameField);
        authorField = findViewById(R.id.authorField);
        pagesField = findViewById(R.id.pagesField);
        imgUrlField = findViewById(R.id.imgUrlField);
        shortDescField = findViewById(R.id.shortDescField);
        longDescField = findViewById(R.id.longDescField);
        urlField = findViewById(R.id.urlField);
    }

    private void handleSubmitClick() {
        String name = bookNameField.getText().toString();
        String author = authorField.getText().toString();
        String pages = pagesField.getText().toString();
        String imgUrl = imgUrlField.getText().toString();
        String shortDescription = shortDescField.getText().toString();
        String longDescription = longDescField.getText().toString();
        String url = urlField.getText().toString();
        if ("".equals(name) || "".equals(author) || "".equals(pages) || "".equals(imgUrl) || "".equals(shortDescription) || "".equals(longDescription) || "".equals(url)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = Utils.getInstance(BookFormActivity.this).getIdForNewBook();
        Book newBook = new Book(id, name, author, Integer.parseInt(pages), imgUrl, shortDescription, longDescription, url);
        if (Utils.getInstance(BookFormActivity.this).addToAllBook(newBook)){
            Intent intent= new Intent(this, BookActivity.class);
            intent.putExtra("bookID",id);
            startActivity(intent);
        }
    }

}