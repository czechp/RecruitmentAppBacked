package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.question.dto.QuestionQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service()
class QuestionQueryRepositoryImpl implements QuestionQueryRepository {
    private QuestionRepository questionRepository;

    @Autowired()
    QuestionQueryRepositoryImpl(final QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionQueryDto> findAll() {
        return questionRepository.findAllBy();
    }

    @Override
    public long countByCategoryAndConfirmed(final Category category, final boolean confirmed) {
        return questionRepository.countByCategoryAndConfirmed(category, confirmed);
    }


    //TODO: ERROR HERE WITH PROJECTIONS
    @Override
    public Set<QuestionQueryDto> findByCategoryWithLimit(final Category category, final int minQuestionAmount) {
        return questionRepository.findByCategoryWithLimit(category).stream()
                .limit(minQuestionAmount)
                .collect(Collectors.toSet());

    }
}
