package co.istad.springdemo2.service.ServiceImpl;

import co.istad.springdemo2.dto.*;
import co.istad.springdemo2.model.Answer;
import co.istad.springdemo2.model.Question;
import co.istad.springdemo2.repository.QuestionRepository;
import co.istad.springdemo2.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    //Convert QuestionRepository to QuestionResponse using Builder b y Lombok
    //Convert Domain Model to DTO
    @Override
    public List<QuestionResponse> findAll() {
        return questionRepository.selectAll()
                .stream()
                .map(question->QuestionResponse.builder()
                        .slug(question.getSlug())
                        .title(question.getTitle())
                        .description(question.getDescription())
                        .viewCount(question.getViewCount())
                        .createdAt(question.getCreatedAt())
                        .createdBy(question.getCreatedBy())
                        .build())
                .toList();
    }


    @Override
    public QuestionDetailResponse findBySlug(String slug) {
        Boolean isExisted=questionRepository.selectAll()
                .stream()
                .anyMatch(q->q.getSlug().equals(slug));
        if(!isExisted){
            System.out.println("The question does not exist");
        }
        Question questions=questionRepository.selectAll()
                .stream()
                .filter(question->question.getSlug().equals(slug))
                .findFirst()
                .orElseThrow();
        //1. Find all answers of that questions
        //2. Stream answers
        //3. map Answer Domain to Answer DTO
        List<AnswerResponse> answers=questions
                .getAnswers()
                .stream()
                .map(answer->AnswerResponse
                        .builder()
                        .uuid(answer.getUuid())
                        .content(answer.getContent())
                        .isAccepted(answer.getIsAccepted())
                        .createdAt(answer.getCreatedAt())
                        .createdBy(answer.getCreatedBy())
                        .build())
                .toList();

        return QuestionDetailResponse.builder()
                .slug(questions.getSlug())
                .title(questions.getTitle())
                .description(questions.getDescription())
                .viewCount(questions.getViewCount())
                .createdAt(questions.getCreatedAt())
                .createdBy(questions.getCreatedBy())
                .answers(answers)
                .build();

    }


    @Override
    public QuestionResponse updateBySlug(String slug, UpdateQuestionRequest updateQuestionRequest) {
        Question questions=questionRepository.selectAll()
                .stream()
                .filter(question->question.getSlug().equals(slug))
                .findFirst()
                .orElseThrow();
        questions.setDescription(updateQuestionRequest.description());
        questionRepository.update(questions);

        return QuestionResponse.builder()
                .slug(questions.getSlug())
                .title(questions.getTitle())
                .description(questions.getDescription())
                .viewCount(questions.getViewCount())
                .createdAt(questions.getCreatedAt())
                .createdBy(questions.getCreatedBy())
                .build();
    }


    @Override
    public QuestionDetailResponse createNewQuestion(CreateQuestionRequest createQuestionRequest) {

        //Validate if the question already exist
        Boolean isExisted=questionRepository.selectAll()
                .stream()
                .anyMatch(q->q.getSlug().equals(createQuestionRequest.slug()));
        if(isExisted){
            System.out.println("Slug is already existed, Please change a new slug.");
        }
        Question question=Question.builder()
                .slug(createQuestionRequest.slug())
                .title(createQuestionRequest.title())
                .description(createQuestionRequest.description())
                .createdBy(createQuestionRequest.createdBy())
                .build();

        question.setId(questionRepository.increaseId());
        question.setCreatedAt(LocalDate.now());
        question.setAnswers(new ArrayList<>());
        question.setViewCount(0L);
        questionRepository.insert(question);

        List<AnswerResponse> answers=question
                .getAnswers()
                .stream()
                .map(answer->AnswerResponse
                        .builder()
                        .uuid(answer.getUuid())
                        .content(answer.getContent())
                        .isAccepted(answer.getIsAccepted())
                        .createdAt(answer.getCreatedAt())
                        .createdBy(answer.getCreatedBy())
                        .build())
                .toList();

        return QuestionDetailResponse.builder()
                .slug(question.getSlug())
                .title(question.getTitle())
                .description(question.getDescription())
                .viewCount(question.getViewCount())
                .createdAt(question.getCreatedAt())
                .createdBy(question.getCreatedBy())
                .answers(answers)
                .build();
    }


    @Override
    public QuestionDetailResponse viewCountBySlug(String slug, String user) {
        //validate slug
        Boolean isExisted=questionRepository.selectAll()
                .stream()
                .anyMatch(q->q.getSlug().equals(slug));
        if(!isExisted){
            System.out.println("The question does not exist");
        }
        Question question=questionRepository.selectAll()
                .stream()
                .filter(q->q.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(null);
        if(!user.equals(question.getCreatedBy())){
            question.setViewCount(question.getViewCount()+1);
        }

        List<AnswerResponse> answers=question.getAnswers()
                .stream()
                .map(answer->AnswerResponse
                        .builder()
                        .uuid(answer.getUuid())
                        .content(answer.getContent())
                        .isAccepted(answer.getIsAccepted())
                        .createdAt(LocalDate.now())
                        .createdBy(answer.getCreatedBy())
                        .build())
                .toList();
        return QuestionDetailResponse.builder()
                .slug(question.getSlug())
                .title(question.getTitle())
                .description(question.getDescription())
                .viewCount(question.getViewCount())
                .answers(answers)
                .createdAt(LocalDate.now())
                .createdBy(question.getCreatedBy())
                .build();
    }


    @Override
    public void deleteBySlug(String slug) {
        Question question =questionRepository.selectAll()
                .stream()
                .filter(q->q.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(null);
        questionRepository.deleteQuestion(question);
    }
}
