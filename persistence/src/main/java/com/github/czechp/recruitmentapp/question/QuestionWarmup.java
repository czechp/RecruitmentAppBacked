package com.github.czechp.recruitmentapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Profile("development")
@Component()
class QuestionWarmup {
    private QuestionRepository questionRepository;

    @Autowired()
    public QuestionWarmup(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    void init() {
        Question question1 = new Question("Question 1", Category.ELECTRIC);
        Question question2 = new Question("Question 2", Category.PLC);
        Question question3 = new Question("Question 3", Category.SCHEMATIC);

        Answer answer1 = new Answer("Answer 1", false);
        Answer answer2 = new Answer("Answer 2", true);
        Answer answer3 = new Answer("Answer 3", false);
        Answer answer4 = new Answer("Answer 4", false);

        Arrays.asList(
                answer1,
                answer2,
                answer3,
                answer4
        ).stream()
                .forEach(answer -> question1.addAnswer(answer));

        Arrays.asList(
                question1,
                question2,
                question3
        ).stream()
                .forEach(question -> questionRepository.save(question));
    }
}
