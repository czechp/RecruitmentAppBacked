package com.github.czechp.recruitmentapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service()
class AnswerCommandRepositoryImpl implements AnswerCommandRepository {
    private final AnswerRepository answerRepository;

    @Autowired()
    AnswerCommandRepositoryImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Optional<Answer> findById(long answerId) {
        return answerRepository.findById(answerId);
    }
}
