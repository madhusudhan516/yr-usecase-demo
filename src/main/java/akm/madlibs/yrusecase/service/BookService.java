package akm.madlibs.yrusecase.service;


import akm.madlibs.yrusecase.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class BookService {
    private final List<Book> books =  new ArrayList<>(30);

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Integer bookId) {
        books.removeIf(book -> book.getBookId().equals(bookId));
    }

    public void updateBook(Integer bookId, Book book) {
        books.stream()
                .filter(b -> b.getBookId().equals(bookId))
                .findFirst()
                .ifPresent(b -> {
                    b.setTitle(book.getTitle());
                    b.setAuthor(book.getAuthor());
                    b.setGenre(book.getGenre());
                    b.setAvailable(book.isAvailable());
                });
    }


    public boolean borrowBook(Integer bookId) {
        return books.stream()
                .anyMatch(b -> b.getBookId().equals(bookId) && b.isAvailable());
    }

    public List<Book> getAvailableBooks(Predicate<Book> predicate) {
        return this.books.stream().filter(predicate).toList();
    }
}
