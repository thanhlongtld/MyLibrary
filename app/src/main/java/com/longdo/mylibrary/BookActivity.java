package com.longdo.mylibrary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    private String BOOK_ID_KEY = "bookID";

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavorite, btnReadBook, btnDelete, btnUpdate;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();


        //TODO: Get the data from recycler view in here
        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance(this).getBookByID(bookId);
                if (incomingBook != null) {
                    setData(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleFavoriteBoooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleReadBook(incomingBook);
                    handleDeleteBook(incomingBook);
                }
            }
        }

    }

    private void handleCurrentlyReadingBooks(final Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance(this).getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;
        for (Book b : currentlyReadingBooks) {
            if (b.getId() == book.getId()) {
                existInCurrentlyReadingBooks = true;
            }
        }
        if (existInCurrentlyReadingBooks) {
            btnAddToCurrentlyReading.setBackgroundColor(getResources().getColor(R.color.pink));
            btnAddToCurrentlyReading.setText("Delete From Currently Reading");
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).removeFromCurrentlyReading(book)) {
                        Toast.makeText(BookActivity.this, "Remove From Currently Reading Successfully", Toast.LENGTH_SHORT).show();
                        btnAddToCurrentlyReading.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        btnAddToCurrentlyReading.setText("Currently Reading");
                    }
                }
            });
        } else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToCurrentlyReading(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleFavoriteBoooks(final Book book) {
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();
        boolean existInFavoriteBooks = false;
        for (Book b : favoriteBooks) {
            if (b.getId() == book.getId()) {
                existInFavoriteBooks = true;
            }
        }
        if (existInFavoriteBooks) {
            btnAddToFavorite.setText("Delete From Favorite");
            btnAddToFavorite.setBackgroundColor(getResources().getColor(R.color.pink));
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).removeFromFavorite(book)) {
                        Toast.makeText(BookActivity.this, "Delete from favorite successfully", Toast.LENGTH_SHORT).show();
                        btnAddToFavorite.setText("Add To Favorites");
                        btnAddToFavorite.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }
                }
            });
        } else {
            btnAddToFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToFavorite(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavoriteActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance(this).getWantToReadBooks();
        boolean existInWantToReadBooks = false;
        for (Book b : wantToReadBooks) {
            if (b.getId() == book.getId()) {
                existInWantToReadBooks = true;
            }
        }
        if (existInWantToReadBooks) {
            btnAddToWantToRead.setText("Delete From Wish List");
            btnAddToWantToRead.setBackgroundColor(getResources().getColor(R.color.pink));
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).removeFromWantToRead(book)) {
                        Toast.makeText(BookActivity.this, "Delete From Wish List Successfully", Toast.LENGTH_SHORT).show();
                        btnAddToWantToRead.setText("Want To Read");
                        btnAddToWantToRead.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }
                }
            });
        } else {
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToWantToRead(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBook();
        boolean existInAlreadyBooks = false;
        for (Book b : alreadyReadBooks) {
            if (b.getId() == book.getId()) {
                existInAlreadyBooks = true;
            }
        }
        if (existInAlreadyBooks) {
            btnAddToAlreadyRead.setText("Delete From Already Read");
            btnAddToAlreadyRead.setBackgroundColor(getResources().getColor(R.color.pink));
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).removeFromAlreadyRead(book)) {
                        Toast.makeText(BookActivity.this, "Remove From Already Read Successfully", Toast.LENGTH_SHORT).show();
                        btnAddToAlreadyRead.setText("Already Read");
                        btnAddToAlreadyRead.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }
                }
            });
        } else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance(BookActivity.this).addToAlreadyRead(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleReadBook(final Book book) {
        btnReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent readBookIntent = new Intent(BookActivity.this, WebsiteActivity.class);
                readBookIntent.putExtra("url", book.getUrl());
                startActivity(readBookIntent);
            }
        });
    }

    private void handleDeleteBook(final Book book) {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                boolean isDelete = Utils.getInstance(BookActivity.this).deleteBookByID(book.getId());
                if (isDelete) {
                    Toast.makeText(BookActivity.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(BookActivity.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(BookActivity.this, "Something went wrong, please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDescription());
        Glide.with(this)
                .asBitmap()
                .load(book.getImgUrl())
                .into(bookImage);
    }

    private void initViews() {
        txtAuthor = findViewById(R.id.txtAuthor);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPage);
        txtDescription = findViewById(R.id.longDescText);

        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToFavorite = findViewById(R.id.btnAddToFavorites);
        btnAddToWantToRead = findViewById(R.id.btnAddToWishList);

        bookImage = findViewById(R.id.imgBook);
        btnReadBook = findViewById(R.id.btnReadBook);
        btnDelete = findViewById(R.id.btnDeleteBook);
    }
}