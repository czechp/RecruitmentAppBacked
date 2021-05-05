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
        Question question2 = new Question("Question 2", Category.ELECTRIC);
        Question question3 = new Question("Question 3", Category.ELECTRIC);

        Arrays.asList(question1, question2, question3)
                .stream()
                .forEach(question -> {
                    for (int i = 0; i < 4; i++) {
                        Answer answer = new Answer("0000" + i, false);
                        if (i == 3)
                            answer.setCorrect(true);
                        question.addAnswer(answer);
                    }
                    question.setConfirmed(true);
                    questionRepository.save(question);
                });
    }
}
