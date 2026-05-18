package akm.madlibs.yrusecase.controller;

import akm.madlibs.yrusecase.model.Book;
import akm.madlibs.yrusecase.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public void addBook(@RequestBody Book book) {

        Book newBook = Book.builder()
                .author(book.getAuthor())
                .title(book.getTitle())
                .bookId(book.getBookId())
                .genre(book.getGenre())
                .available(true).build();
        bookService.addBook(newBook);
    }

    @PutMapping("/update")
    public void updateBook(@RequestParam Integer bookId, @RequestBody Book book) {
        bookService.updateBook(bookId, book);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(bookService.getBooks(),  HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Book> getBooksByAuthor(@RequestParam(required = false) String author,  @RequestParam(required = false) String genre, @RequestParam(required = false) String title) {

        Predicate<Book> filter = book -> true; // default: no filtering

        if (author != null && !author.isBlank()) {
            filter = book -> author.equalsIgnoreCase(book.getAuthor());
        } else if (genre != null && !genre.isBlank()) {
            filter = book -> genre.equalsIgnoreCase(book.getGenre());
        } else if (title != null && !title.isBlank()) {
            filter = book -> title.equalsIgnoreCase(book.getTitle());
        }

        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAvailableBooks(filter)).getBody();
    }
}
