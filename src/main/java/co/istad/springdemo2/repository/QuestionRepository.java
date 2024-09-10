package co.istad.springdemo2.repository;


import co.istad.springdemo2.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepository {

    private final List<Question> questions;

    //Find All Question
    public List<Question> selectAll(){
        return questions;
    }

    //update description by slug
    public void update(Question newQuestion){
        List<Question> newQuestions=questions.stream()
                .filter(q->q.getSlug().equals(newQuestion.getSlug()))
                .map(q->newQuestion)
                .toList();
        System.out.println(newQuestions);
    }

    //create new question
    public void insert(Question question){
        questions.add(question);
    }

    //increase id
    public Integer increaseId(){
        return questions.size()+1;
    }

    //Delete question
    public void deleteQuestion(Question question){
        questions.remove(question);
    }
}
