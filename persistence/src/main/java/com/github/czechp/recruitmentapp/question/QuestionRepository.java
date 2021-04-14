package com.github.czechp.recruitmentapp.question;


import com.github.czechp.recruitmentapp.question.dto.QuestionQueryDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@org.springframework.stereotype.Repository()
interface QuestionRepository extends Repository<Question, Long> {
    Question save(Question question);

    List<QuestionQueryDto> findAllBy();

    Optional<Question> findById(long questionId);

    void deleteById(long questionId);

    boolean existsById(long questionId);

    long countByCategoryAndConfirmed(final Category category, final boolean confirmed);

    @Query(value = "SELECT q FROM Question q WHERE category =:category")
    Set<QuestionQueryDto> findByCategoryWithLimit(@Param("category") Category category);
}
