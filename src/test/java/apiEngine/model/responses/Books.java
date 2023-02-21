package apiEngine.model.responses;

import apiEngine.model.Book;

import java.util.List;

public class Books {

    public Books() {
    }

    public Books(List<Book> books) {
        this.books = books;
    }

    public List<Book> books;


}
