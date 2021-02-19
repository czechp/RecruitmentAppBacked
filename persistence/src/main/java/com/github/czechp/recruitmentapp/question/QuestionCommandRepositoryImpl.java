package com.github.czechp.recruitmentapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service()
class QuestionCommandRepositoryImpl implements QuestionCommandRepository {
    private QuestionRepository questionRepository;

    @Autowired()
    QuestionCommandRepositoryImpl(final QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> findById(long questionId) {
        return questionRepository.findById(questionId);
    }


}
