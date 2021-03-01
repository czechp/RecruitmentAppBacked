package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.exception.BadRequestException;
import com.github.czechp.recruitmentapp.exception.EntityNotFoundException;
import com.github.czechp.recruitmentapp.question.dto.AnswerCommandDto;
import com.github.czechp.recruitmentapp.question.dto.QuestionCommandDto;
import com.github.czechp.recruitmentapp.utility.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.File;

@Service()
class QuestionCommandService {
    private final QuestionCommandRepository questionCommandRepository;
    private final AnswerCommandRepository answerCommandRepository;
    private final FileStorage fileStorage;

    @Autowired()
    QuestionCommandService(QuestionCommandRepository questionCommandRepository, AnswerCommandRepository answerCommandRepository, final FileStorage fileStorage) {
        this.questionCommandRepository = questionCommandRepository;
        this.answerCommandRepository = answerCommandRepository;
        this.fileStorage = fileStorage;
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

    @Transactional()
    public void addAnswer(final long questionId, final AnswerCommandDto answerCommandDto) {
        Question question = questionCommandRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question id: " + questionId));
        if (question.getAnswers().size() >= 4)
            throw new BadRequestException("Question gat maximum numbers of answers");

        boolean alreadyCorrectAnswerExists = question.getAnswers()
                .stream()
                .anyMatch(answer -> answer.isCorrect());

        if (alreadyCorrectAnswerExists && answerCommandDto.isCorrect())
            throw new BadRequestException("Question got already correct answer");

        if (!alreadyCorrectAnswerExists && !answerCommandDto.isCorrect() && question.getAnswers().size() >= 3)
            throw new BadRequestException("At least one answer gotta be correct");

        question.addAnswer(AnswerFactory.commandDtoToPojo(answerCommandDto));

    }

    @Transactional()
    public void deleteAnswerById(final long answerId) {
        Answer answer = answerCommandRepository.findById(answerId)
                .orElseThrow(() -> new EntityNotFoundException("Answer id: " + answerId));
        answer.getQuestion().setConfirmed(false);
        answer.getQuestion().removeAnswer(answer);
    }

    @Transactional()
    public void confirmQuestion(long questionId, boolean confirmation) {
        Question question = questionCommandRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question id: " + questionId));
        if (question.getAnswers().size() < 4)
            throw new BadRequestException("Question has incomplete answers set");

        question.setConfirmed(confirmation);
    }


    //TODO: return to this place
    @Transactional()
    public void addImage(final long questionId, final String fileName, final File file){
        Question question = questionCommandRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question id: " + questionId));
        fileStorage.uploadFile(questionId, fileName, file);
    }

}
