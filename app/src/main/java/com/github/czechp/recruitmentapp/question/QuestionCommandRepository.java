package com.github.czechp.recruitmentapp.question;

import java.util.Optional;

interface QuestionCommandRepository {
    Question save(Question question);

    Optional<Question> findById(long questionId);
}
