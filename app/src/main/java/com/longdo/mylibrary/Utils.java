package com.longdo.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class Utils {
    private static Utils instance;

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVORITE_BOOKS = "favorite_books";

    private SharedPreferences sharedPreferences;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if (null == getAllBooks()) {
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAlreadyReadBook()) {
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getWantToReadBooks()) {
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getFavoriteBooks()) {
            editor.putString(FAVORITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book(1, "Harry Potter and the Sorcerer’s Stone", "J. K. Rowling", 327, "https://online.anyflip.com/eeib/ywzj/files/mobile/1.jpg", "The first novel in the Harry Potter Series", "This novel follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry. Harry makes close friends and a few enemies during his first year at the school, and with the help of his friends, Harry faces an attempted comeback by the dark wizard Lord Voldemort, who killed Harry's parents, but failed to kill Harry when he was just 15 months old.", "https://readonlinenovels.com/nv/6eb2dc222c508599/harry-potter/165757-h"));
        books.add(new Book(2, "Harry Potter and the Chamber of Secrets", "J. K. Rowling", 359, "https://salt.tikicdn.com/cache/280x280/ts/product/9b/bd/0b/218caab7751f6b532cf7b1bd950099a2.jpg", "The second novel in the Harry Potter Series", "The novel follows Harry's second year at Hogwarts School of Witchcraft and Wizardry, during which a series of messages on the walls of the school's corridors warn that the \"Chamber of Secrets\" has been opened and that the \"heir of Slytherin\" would kill all pupils who do not come from all-magical families. These threats are found after attacks that leave residents of the school petrified. Throughout the year, Harry and his friends Ron and Hermione investigate the attacks.", "https://readonlinenovels.com/nv/6eb2dc222c508599/harry-potter/165774-h"));
        books.add(new Book(3, "Harry Potter and the Prisoner of Azkaban", "J. K. Rowling", 455, "https://images-na.ssl-images-amazon.com/images/I/81lAPl9Fl0L.jpg", "The third novel in the Harry Potter Series", "The novel follows Harry Potter, a young wizard, in his third year at Hogwarts School of Witchcraft and Wizardry. Along with friends Ronald Weasley and Hermione Granger, Harry investigates Sirius Black, an escaped prisoner from Azkaban, the wizard prison, believed to be one of Lord Voldemort's old allies.", "https://readonlinenovels.com/nv/6eb2dc222c508599/harry-potter/165792-h"));
        books.add(new Book(4, "Harry Potter and the Goblet of Fire", "J. K. Rowling", 755, "https://d2e111jq13me73.cloudfront.net/sites/default/files/styles/product_image_aspect_switcher_170w/public/product-images/csm-book/6575-orig.jpg?itok=0qVVmY55", "The fourth novel in the Harry Potter Series", "The novel follows Harry Potter, a wizard in his fourth year at Hogwarts School of Witchcraft and Wizardry, and the mystery surrounding the entry of Harry's name into the Triwizard Tournament, in which he is forced to compete.", "https://readonlinenovels.com/nv/6eb2dc222c508599/harry-potter/165839-h"));
        books.add(new Book(5, "Harry Potter and the Order of the Phoenix", "J. K. Rowling", 891, "https://cdn.waterstones.com/bookjackets/large/9781/4088/9781408855690.jpg", "The fifth novel in the Harry Potter Series", "The novel follows Harry Potter's struggles through his fifth year at Hogwarts School of Witchcraft and Wizardry, including the surreptitious return of the antagonist Lord Voldemort, O.W.L. exams, and an obstructive Ministry of Magic.", "https://readonlinenovels.com/nv/6eb2dc222c508599/harry-potter/165851-h"));
        books.add(new Book(6, "Harry Potter and the Half-Blood Prince", "J. K. Rowling", 671, "https://www.bigw.com.au/medias/sys_master/images/images/h53/h33/10670482063390.jpg", "The sixth novel in the Harry Potter Series", "Set during Harry Potter's sixth year at Hogwarts, the novel explores the past of the boy wizard's nemesis, Lord Voldemort, and Harry's preparations for the final battle against Voldemort alongside his headmaster and mentor Albus Dumbledore.", "https://readonlinenovels.com/nv/6eb2dc222c508599/harry-potter/165889-h"));
        books.add(new Book(7, "Harry Potter and the Deathly Hallows", "J. K. Rowling", 781, "https://images-na.ssl-images-amazon.com/images/I/811t1pfIZXL.jpg", "The seventh novel in the Harry Potter Series", "The novel chronicles the events directly following Harry Potter and the Half-Blood Prince (2005) and the final confrontation between the wizards Harry Potter and Lord Voldemort.", "https://readonlinenovels.com/nv/6eb2dc222c508599/harry-potter/165919-h"));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();
    }

    public static Utils getInstance(Context context) {
        if (null == instance) {
            instance = new Utils(context);
        }
        return instance;
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBook() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        return books;
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS, null), type);
        return books;
    }

    public Book getBookByID(int id) {
        ArrayList<Book> books = getAllBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBook();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavorite(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBook();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFavorite(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books) {
            for (Book b : books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getIdForNewBook() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();

        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return (books != null) ? books.size() + 1 : 1;
    }

    public boolean addToAllBook(Book book) {
        ArrayList<Book> books = getAllBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALL_BOOKS_KEY);
                editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public ArrayList<Book> getFromAllBooksByName(String name) {
        ArrayList<Book> result = new ArrayList<Book>();
        ArrayList<Book> books = getAllBooks();
        String lowerName = name.toLowerCase();
        for (Book b : books) {
            if (b.getName().toLowerCase().contains(lowerName)) {
                result.add(b);
            }
        }
        return result;
    }

    public ArrayList<Book> getFromAlreadyReadBooksByName(String name) {
        ArrayList<Book> result = new ArrayList<Book>();
        ArrayList<Book> books = getAlreadyReadBook();
        String lowerName = name.toLowerCase();
        for (Book b : books) {
            if (b.getName().toLowerCase().contains(lowerName)) {
                result.add(b);
            }
        }
        return result;
    }

    public ArrayList<Book> getFromCurrentlyReadingBooksByName(String name) {
        ArrayList<Book> result = new ArrayList<Book>();
        ArrayList<Book> books = getCurrentlyReadingBooks();
        String lowerName = name.toLowerCase();
        for (Book b : books) {
            if (b.getName().toLowerCase().contains(lowerName)) {
                result.add(b);
            }
        }
        return result;
    }

    public ArrayList<Book> getFromFavoriteBooksByName(String name) {
        ArrayList<Book> result = new ArrayList<Book>();
        ArrayList<Book> books = getFavoriteBooks();
        String lowerName = name.toLowerCase();
        for (Book b : books) {
            if (b.getName().toLowerCase().contains(lowerName)) {
                result.add(b);
            }
        }
        return result;
    }

    public ArrayList<Book> getFromWantToReadBooksByName(String name) {
        ArrayList<Book> result = new ArrayList<Book>();
        ArrayList<Book> books = getWantToReadBooks();
        String lowerName = name.toLowerCase();
        for (Book b : books) {
            if (b.getName().toLowerCase().contains(lowerName)) {
                result.add(b);
            }
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean deleteBookByID(int id) {

        ArrayList<Book> allBooks = getAllBooks();
        ArrayList<Book> alreadyReadBooks = getAlreadyReadBook();
        ArrayList<Book> currentlyReading = getCurrentlyReadingBooks();
        ArrayList<Book> favoriteBooks = getFavoriteBooks();
        ArrayList<Book> wantToReadBooks = getWantToReadBooks();

        allBooks.removeIf(n -> n.getId() == id);
        if (!setAllBooks(allBooks)) {
            return false;
        }
        ;
        alreadyReadBooks.removeIf(n -> n.getId() == id);
        if (!setAlreadyBooks(alreadyReadBooks)) return false;

        currentlyReading.removeIf(n -> n.getId() == id);
        if (!setCurrentlyReading(currentlyReading)) return false;

        favoriteBooks.removeIf(n -> n.getId() == id);
        if (!setFavoriteBooks(favoriteBooks)) return false;

        wantToReadBooks.removeIf(n -> n.getId() == id);
        if (!setWantToReadBooks(wantToReadBooks)) return false;

        return true;
    }

    public boolean setAllBooks(ArrayList<Book> books) {
        if (null != books) {
            Gson gson = new Gson();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(ALL_BOOKS_KEY);
            editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
            editor.commit();
            return true;
        }
        return false;
    }

    public boolean setAlreadyBooks(ArrayList<Book> books) {
        if (null != books) {
            Gson gson = new Gson();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(ALREADY_READ_BOOKS);
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
            editor.commit();
            return true;
        }
        return false;
    }

    public boolean setCurrentlyReading(ArrayList<Book> books) {
        if (null != books) {
            Gson gson = new Gson();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(CURRENTLY_READING_BOOKS);
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
            editor.commit();
            return true;
        }
        return false;
    }

    public boolean setFavoriteBooks(ArrayList<Book> books) {
        if (null != books) {
            Gson gson = new Gson();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(FAVORITE_BOOKS);
            editor.putString(FAVORITE_BOOKS, gson.toJson(books));
            editor.commit();
            return true;
        }
        return false;
    }

    public boolean setWantToReadBooks(ArrayList<Book> books) {
        if (null != books) {
            Gson gson = new Gson();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(WANT_TO_READ_BOOKS);
            editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));
            editor.commit();
            return true;
        }
        return false;
    }
}
