package co.istad.springdemo2.service;


import co.istad.springdemo2.dto.CreateAnswerRequest;
import co.istad.springdemo2.dto.UpdateAnswerRequest;

public interface AnswerService {
    void createAnswer(String slug, CreateAnswerRequest createAnswerRequest);
    void updateAnswer(String slug,String uuid, UpdateAnswerRequest updateAnswerRequest);
    void deleteAnswer(String slug, String uuid);
}
