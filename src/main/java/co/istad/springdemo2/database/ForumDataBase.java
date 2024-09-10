package co.istad.springdemo2.database;

import co.istad.springdemo2.model.Answer;
import co.istad.springdemo2.model.Question;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ForumDataBase {
    @Bean
    public List<Question> questions(){
        Question q1=new Question();
        q1.setId(1);
        q1.setSlug("cannot-resolve-dependencies-in-spring-boot-when-creating-a-new-application");
        q1.setTitle("Cannot resolve dependencies in spring boot when creating a new application");
        q1.setDescription("Cannot resolve dependencies in spring boot when creating a new application");
        q1.setViewCount(0L);
        q1.setAnswers(new ArrayList<>());
        q1.setCreatedAt(LocalDate.now());
        q1.setCreatedBy("admin");

        Question q2=new Question();
        q2.setId(2);
        q2.setSlug("jenkins-build-failure-with-maven-dependencies");
        q2.setTitle("Jenkins build failure with maven dependencies");
        q2.setDescription("Jenkins build failure with maven dependencies");
        q2.setViewCount(0L);
        q2.setCreatedAt(LocalDate.now());
        q2.setCreatedBy("Bob");
        q2.setAnswers(List.of(
                Answer.builder()
                        .uuid("057b2fd9-c867-484c-9c5d-bd187d4bc510")
                        .content("You should be able upload the 3rd party jar into artifactory which would then allow jenkins to access it during the build")
                        .createdAt(LocalDate.now())
                        .createdBy("sreyny")
                        .isAccepted(true)
                        .build()
        ));

        return new ArrayList<>(List.of(q1,q2));
    }
}
