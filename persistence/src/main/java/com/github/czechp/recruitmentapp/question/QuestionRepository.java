package com.github.czechp.recruitmentapp.question;


import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository()
interface QuestionRepository extends Repository<Question, Long> {
    Question save(Question question);

    List<QuestionDto> findAllBy();

    Optional<Question> findById(long questionId);
}
