package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.exception.BadRequestException;
import com.github.czechp.recruitmentapp.question.dto.QuestionQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service()
public class QuestionQueryService {
    private static final int MIN_QUESTION_AMOUNT = 3;
    private final QuestionQueryRepository questionQueryRepository;

    @Autowired()
    QuestionQueryService(QuestionQueryRepository questionQueryRepository) {
        this.questionQueryRepository = questionQueryRepository;
    }

    public List<QuestionQueryDto> findAll() {
        return questionQueryRepository.findAll();
    }

    //TODO: end when test controller will be ready
    Set<QuestionQueryDto> getQuestionForTest(final String candidate) {
        Set<QuestionQueryDto> questions = new HashSet<>();
        switch (candidate.toUpperCase()) {
            case "ELECTRIC":
                questions = getQuestionForCategory(Category.ELECTRIC);
                break;
            default:
                ;
        }
        return questions;
    }

    private Set<QuestionQueryDto> getQuestionForCategory(final Category category) {
        long questionAmount = questionQueryRepository.countByCategoryAndConfirmed(category, true);
        if (questionAmount >= MIN_QUESTION_AMOUNT)
            return questionQueryRepository.findByCategoryWithLimit(category, MIN_QUESTION_AMOUNT);
        else
            throw new BadRequestException("Not enough questions in database");
    }
}
