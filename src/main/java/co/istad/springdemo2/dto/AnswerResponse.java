package co.istad.springdemo2.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AnswerResponse(
        String uuid,
         String content,
         Boolean isAccepted,
         LocalDate createdAt,
         String createdBy
) {
}
