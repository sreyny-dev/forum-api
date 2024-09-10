package co.istad.springdemo2.service;

import co.istad.springdemo2.dto.CreateQuestionRequest;
import co.istad.springdemo2.dto.QuestionDetailResponse;
import co.istad.springdemo2.dto.QuestionResponse;
import co.istad.springdemo2.dto.UpdateQuestionRequest;

import java.util.List;

public interface QuestionService {
    List<QuestionResponse> findAll();
    QuestionDetailResponse findBySlug(String slug);
    QuestionResponse updateBySlug(String slug, UpdateQuestionRequest updateQuestionRequest);
    QuestionDetailResponse createNewQuestion(CreateQuestionRequest createQuestionRequest);
    QuestionDetailResponse viewCountBySlug(String slug, String user);
    void deleteBySlug(String slug);
}
