package com.github.czechp.recruitmentapp.question;


import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository()
interface QuestionRepository extends Repository<Question, Long> {
    Question save(Question question);
    List<QuestionDto> findAllBy();
}
