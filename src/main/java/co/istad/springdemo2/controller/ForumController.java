package co.istad.springdemo2.controller;

import co.istad.springdemo2.dto.*;
import co.istad.springdemo2.service.AnswerService;
import co.istad.springdemo2.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class ForumController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    //Find ALl Questions
    @GetMapping
    List<QuestionResponse> findAll(){
       return questionService.findAll();
    }
    //Find Question By Slug
    @GetMapping("/{slug}")
    QuestionDetailResponse findBySlug(@PathVariable String slug){
        return questionService.findBySlug(slug);
    }

    @PutMapping("/{slug}")
    QuestionResponse updateBySlug(@PathVariable String slug,
                      @RequestBody UpdateQuestionRequest updateQuestionRequest){
        return questionService.updateBySlug(slug, updateQuestionRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    QuestionDetailResponse createNewQuestion(@RequestBody CreateQuestionRequest createQuestionRequest){
        return questionService.createNewQuestion(createQuestionRequest);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{slug}/answers")
    void createNewAnswer(@PathVariable String slug, @RequestBody CreateAnswerRequest createAnswerRequest){
         answerService.createAnswer(slug, createAnswerRequest);
    }

    @GetMapping("/{slug}/{user}")
    QuestionDetailResponse viewCountBySlug(@PathVariable String slug, @PathVariable String user){
        return questionService.viewCountBySlug(slug, user);
    }

    @DeleteMapping("/{slug}")
    void deleteBySlug(@PathVariable String slug){
        questionService.deleteBySlug(slug);
    }

    //Update answer by slug and id
    @PutMapping("/{slug}/{uuid}")
    void updateAswer(@PathVariable String slug,
                     @PathVariable String uuid,
                     @RequestBody UpdateAnswerRequest updateAnswerRequest){
        answerService.updateAnswer(slug, uuid, updateAnswerRequest);
    }

    //Delete an answer by slug of Question and uuid of answer
    @DeleteMapping("/{slug}/{uuid}")
    void deleteAnswer(@PathVariable String slug,
                      @PathVariable String uuid){
        answerService.deleteAnswer(slug, uuid);
    }
}
