package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service()
class QuestionCommandService {
    private final QuestionCommandRepository questionCommandRepository;

    @Autowired()
    QuestionCommandService(QuestionCommandRepository questionCommandRepository) {
        this.questionCommandRepository = questionCommandRepository;
    }

    QuestionDto save(QuestionDto questionDto) {
        return QuestionFactory.poJoToDto(
                questionCommandRepository.save(QuestionFactory.dtoToPoJo(questionDto))
        );
    }

    @Transactional()
    public QuestionDto update(final long questionId, final Question question) {
        Question result = questionCommandRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question id: " + questionId));
        result.setContent(question.getContent());
        result.setCategory(question.getCategory());

        return QuestionFactory.poJoToDto(result);
    }


    void deleteById(final long questionId) {
        if (questionCommandRepository.existsById(questionId)) {
            questionCommandRepository.deleteById(questionId);
        } else {
            throw new EntityNotFoundException("Question id: " + questionId);
        }
    }
}
