package com.github.czechp.recruitmentapp.question;


import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository()
interface AnswerRepository extends Repository<Answer, Long> {
    Optional<Answer> findById(long answerId);
}

