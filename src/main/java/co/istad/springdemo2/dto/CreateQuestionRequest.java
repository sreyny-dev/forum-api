package co.istad.springdemo2.dto;

import lombok.Builder;

@Builder
public record CreateQuestionRequest(
    String slug,
    String title,
    String description,
    String createdBy
) {
}
