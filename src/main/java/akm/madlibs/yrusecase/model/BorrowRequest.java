package akm.madlibs.yrusecase.model;

import java.util.List;

public class BorrowRequest {
    private Member member;
    private List<Book> books;

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}