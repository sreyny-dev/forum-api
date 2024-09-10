package co.istad.springdemo2.repository;


import co.istad.springdemo2.model.Answer;
import co.istad.springdemo2.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AnswerRepository {
    private final List<Question> questions;
    private final List<Answer> answers;

    //generate random UUID
    public String uuidGenerator(Answer answer){
        String uuid= UUID.randomUUID().toString();
        answer.setUuid(uuid);
        return uuid;
    }

    //UPDATE AN ANSWER
    public void updateAnswer(Answer newAnswer){
        List<Answer> newAnswers =answers.stream()
                .filter(a->a.getUuid().equals(newAnswer.getUuid()))
                .map(a->newAnswer)
                .toList();
    }

}
