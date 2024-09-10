package co.istad.springdemo2.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Integer id;
    private String slug;
    private String title;
    private String description;
    private Long viewCount;
    private LocalDate createdAt;
    private String createdBy;
    private List<Answer> answers;
}
