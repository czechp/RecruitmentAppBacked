package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service()
class QuestionCommandService {
    private final QuestionCommandRepository questionCommandRepository;

    @Autowired()
    QuestionCommandService(QuestionCommandRepository questionCommandRepository) {
        this.questionCommandRepository = questionCommandRepository;
    }

    QuestionDto save(Question question) {
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

    @Transactional()
    public QuestionDto update(long questionId, Question question) {
        Question result = questionCommandRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question id: " + questionId));
        result.setContent(question.getContent());
        result.setCategory(question.getCategory());

        return toDto(result);
    }
}
