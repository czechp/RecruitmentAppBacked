package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.exception.EntityNotFoundException;
import com.github.czechp.recruitmentapp.question.dto.QuestionCommandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service()
class QuestionCommandService {
    private final QuestionCommandRepository questionCommandRepository;

    @Autowired()
    QuestionCommandService(QuestionCommandRepository questionCommandRepository) {
        this.questionCommandRepository = questionCommandRepository;
    }

    void save(QuestionCommandDto questionCommandDto) {
        questionCommandRepository.save(QuestionFactory.commandDtoToPojo(questionCommandDto));
    }

    @Transactional()
    public void update(final long questionId, final @Valid() QuestionCommandDto question) {
        Question result = questionCommandRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question id: " + questionId));
        result.setContent(question.getContent());
        result.setCategory(question.getCategory());
    }


    void deleteById(final long questionId) {
        if (questionCommandRepository.existsById(questionId)) {
            questionCommandRepository.deleteById(questionId);
        } else {
            throw new EntityNotFoundException("Question id: " + questionId);
        }
    }
}
