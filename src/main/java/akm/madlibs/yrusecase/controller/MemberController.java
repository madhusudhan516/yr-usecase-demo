package akm.madlibs.yrusecase.controller;


import akm.madlibs.yrusecase.model.Book;
import akm.madlibs.yrusecase.model.BorrowRequest;
import akm.madlibs.yrusecase.model.Member;
import akm.madlibs.yrusecase.service.MemberService;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/add")
    public @Nullable String addMember(@RequestBody Member member) {
        memberService.addMember(member);
        return ResponseEntity.status(200).body("Member added successfully").getBody();
    }

    @PostMapping("/borrow")
    public @Nullable String borrowBook(@RequestBody BorrowRequest borrowRequest) {
        memberService.borrowBook(borrowRequest.getMember(), borrowRequest.getBooks());
        return ResponseEntity.status(200).body("Book(s) borrowed successfully").getBody();
    }

    @PostMapping("/issue")
    public @Nullable String issueBook(@RequestBody Member member, @RequestBody List<Book> books) {
        memberService.returnBook(member, books);
        return ResponseEntity.status(200).body("Book issued successfully").getBody();
    }


    @GetMapping("/all-borrowers")
    public List<Member> getAllBorrowers() {
        return memberService.getAllBorrowers();
    }

}
