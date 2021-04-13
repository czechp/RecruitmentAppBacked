package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.exception.BadRequestException;
import com.github.czechp.recruitmentapp.question.dto.QuestionQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class QuestionQueryService {
    private static final int MIN_QUESTION_AMOUNT = 1;
    private final QuestionQueryRepository questionQueryRepository;

    @Autowired()
    QuestionQueryService(QuestionQueryRepository questionQueryRepository) {
        this.questionQueryRepository = questionQueryRepository;
    }

    public List<QuestionQueryDto> findAll() {
        return questionQueryRepository.findAll();
    }

    List<QuestionQueryDto> getQuestionForTest(final String candidate) {
        List<QuestionQueryDto> questions = new ArrayList<>();
        switch (candidate.toUpperCase()) {
            case "ELECTRIC":
                questions = getQuestionForCategory(Category.ELECTRIC);
                break;
        }
        return questions;
    }

    private List<QuestionQueryDto> getQuestionForCategory(final Category category) {
        long questionAmount = questionQueryRepository.countByCategoryAndConfirmed(category, true);
        if (questionAmount >= MIN_QUESTION_AMOUNT)
            return questionQueryRepository.findByCategoryWithLimit(category, MIN_QUESTION_AMOUNT);
        else
            throw new BadRequestException("Not enough questions in database");
    }
}
