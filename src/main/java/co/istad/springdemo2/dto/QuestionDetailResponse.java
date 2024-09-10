package co.istad.springdemo2.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record QuestionDetailResponse(
         String slug,
         String title,
         String description,
         Long viewCount,
         LocalDate createdAt,
         String createdBy,
         List<AnswerResponse>answers
) {
}
