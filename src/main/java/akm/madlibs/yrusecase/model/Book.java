package akm.madlibs.yrusecase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer bookId;
    private String title;
    private String author;
    private String genre;
    private boolean available;

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + "}";
    }
}
