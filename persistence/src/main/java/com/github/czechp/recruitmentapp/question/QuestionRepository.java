package com.github.czechp.recruitmentapp.question;


import com.github.czechp.recruitmentapp.question.dto.QuestionQueryDto;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository()
interface QuestionRepository extends Repository<Question, Long> {
    Question save(Question question);

    List<QuestionQueryDto> findAllBy();

    Optional<Question> findById(long questionId);

    void deleteById(long questionId);

    boolean existsById(long questionId);
}
