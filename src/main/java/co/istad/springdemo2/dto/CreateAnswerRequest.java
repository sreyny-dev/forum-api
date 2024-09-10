package co.istad.springdemo2.dto;


import lombok.Builder;

@Builder
public record CreateAnswerRequest(
        String content,
        String createdBy
)
{
}
