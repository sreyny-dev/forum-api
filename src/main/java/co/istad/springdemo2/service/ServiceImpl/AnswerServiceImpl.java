package co.istad.springdemo2.service.ServiceImpl;

import co.istad.springdemo2.dto.AnswerResponse;
import co.istad.springdemo2.dto.CreateAnswerRequest;
import co.istad.springdemo2.dto.UpdateAnswerRequest;
import co.istad.springdemo2.model.Answer;
import co.istad.springdemo2.model.Question;
import co.istad.springdemo2.repository.AnswerRepository;
import co.istad.springdemo2.repository.QuestionRepository;
import co.istad.springdemo2.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    //Convert DTO to Domain
    @Override
    public void createAnswer(String slug, CreateAnswerRequest createAnswerRequest) {
        //Validate the question if it is existed
        Question question=questionRepository.selectAll()
                .stream()
                .filter(q->q.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(null);
        //Map DTO to Domain using Builder
        Answer answer=Answer.builder()
                .content(createAnswerRequest.content())
                .createdAt(LocalDate.now())
                .isAccepted(false)
                .createdBy(createAnswerRequest.createdBy())
                .build();
        answer.setUuid(answerRepository.uuidGenerator(answer));
        List<Answer> answers=new ArrayList<>(question.getAnswers());
        answers.add(answer);
        question.setAnswers(answers);
    }

    //Update Question
    @Override
    public void updateAnswer(String slug,String uuid, UpdateAnswerRequest updateAnswerRequest) {
        //First Find Question
        Question question=questionRepository.selectAll()
                .stream()
                .filter(q->q.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(null);
        //Find an answer by uuid
        Answer answer=question.getAnswers()
                .stream()
                .filter(q->q.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(null);
        //replace content of that question
        //also convert UpdateAnswerRequest to Answer
        answer.setContent(updateAnswerRequest.content());
        answerRepository.updateAnswer(answer);
    }

    @Override
    public void deleteAnswer(String slug, String uuid) {
        Question question=questionRepository.selectAll()
                .stream()
                .filter(q->q.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(null);
        Answer answer=question.getAnswers()
                .stream()
                .filter(q->q.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(null);
        question.getAnswers().remove(answer);
        questionRepository.update(question);
    }
}
