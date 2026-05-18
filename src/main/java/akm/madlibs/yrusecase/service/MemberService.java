package akm.madlibs.yrusecase.service;


import akm.madlibs.yrusecase.model.Book;
import akm.madlibs.yrusecase.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
    private static final int MAX_BOOKS_PER_MEMBER = 5;
    private static final HashMap<Integer, Member> memberRegistry = new HashMap<>();
    private static final HashMap<Integer, List<Book>> members = new HashMap<>();

    private final BookService bookService;

    public MemberService(BookService bookService) {
        this.bookService = bookService;
    }



    public void addMember(Member member) {
        if (!members.containsKey(member.getMemberId())) {
            memberRegistry.put(member.getMemberId(), member);
            members.put(member.getMemberId(), new ArrayList<>());
        } else {
            throw new IllegalStateException("Member with this ID already exists.");
        }
    }


    public void borrowBook(Member member, List<Book> books) {
        List<Book> memberBooks = members.getOrDefault(member.getMemberId(), new ArrayList<>());

        books.forEach(book -> {
                    if (memberBooks.size() < MAX_BOOKS_PER_MEMBER && bookService.borrowBook(book.getBookId())) {
                        memberBooks.add(book);
                        book.setAvailable(false);
                        bookService.updateBook(book.getBookId(), book);

                        logger.info("BORROW SUCCESS | memberId={} memberName={} bookId={} title={}",
                                member.getMemberId(), member.getName(), book.getBookId(), book.getTitle());

                    } else {

                        logger.warn("BORROW FAILED | memberId={} bookId={} reason={}",
                                member.getMemberId(),
                                book.getBookId(),
                                memberBooks.size() >= MAX_BOOKS_PER_MEMBER ? "MAX_LIMIT_REACHED" : "BOOK_UNAVAILABLE");

                        throw new IllegalStateException("Member has already borrowed the maximum number of books.");
                    }
                });

        members.put(member.getMemberId(), memberBooks);
    }


    public void returnBook(Member member, List<Book> books) {
        List<Book> memberBooks = members.getOrDefault(member.getMemberId(), new ArrayList<>());

        books.forEach(book -> {
            if (memberBooks.removeIf(b -> b.getBookId().equals(book.getBookId()))) {
                book.setAvailable(true);
                bookService.updateBook(book.getBookId(), book);
            } else {
                throw new IllegalStateException("Member did not borrow this book.");
            }
        });
    }

    public List<Member> getAllBorrowers() {
        return members.entrySet().stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .map(entry -> memberRegistry.get(entry.getKey()))
                .toList();
    }

}
