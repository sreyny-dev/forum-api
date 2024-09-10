package co.istad.springdemo2.model;

import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private Integer id;
    private String uuid;
    private String content;
    private Boolean isAccepted;
    private LocalDate createdAt;
    private String createdBy;
    private Question question;
}
