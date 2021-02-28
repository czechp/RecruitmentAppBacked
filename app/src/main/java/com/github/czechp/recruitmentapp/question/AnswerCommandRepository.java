package com.github.czechp.recruitmentapp.question;

import java.util.Optional;

interface AnswerCommandRepository {
    Optional<Answer> findById(long answerId);
}
