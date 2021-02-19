package com.github.czechp.recruitmentapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
class QuestionCommandService {
    private final QuestionCommandRepository questionCommandRepository;

    @Autowired()
    QuestionCommandService(QuestionCommandRepository questionCommandRepository) {
        this.questionCommandRepository = questionCommandRepository;
    }

    QuestionDto save(Question question){
        return toDto(questionCommandRepository.save(question));
    }

    private QuestionDto toDto(final Question question) {
        return new QuestionDto() {
            @Override
            public long getId() {
                return question.getId();
            }

            @Override
            public String getContent() {
                return question.getContent();
            }

            @Override
            public Category getCategory() {
                return question.getCategory();
            }
        };
    }
}
