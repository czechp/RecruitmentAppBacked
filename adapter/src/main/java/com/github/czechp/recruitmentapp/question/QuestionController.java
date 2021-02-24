package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.question.dto.AnswerCommandDto;
import com.github.czechp.recruitmentapp.question.dto.QuestionCommandDto;
import com.github.czechp.recruitmentapp.question.dto.QuestionQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api/questions")
@CrossOrigin("*")
class QuestionController {
    private final QuestionCommandService questionCommandService;
    private final QuestionQueryService questionQueryService;

    @Autowired()
    QuestionController(QuestionCommandService questionCommandService, QuestionQueryService questionQueryService) {
        this.questionCommandService = questionCommandService;
        this.questionQueryService = questionQueryService;
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<QuestionQueryDto> findAll() {
        return questionQueryService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    void save(@RequestBody() @Valid final QuestionCommandDto question) {
        questionCommandService.save(question);
    }


    @PutMapping("/{questionId}")
    @ResponseStatus(HttpStatus.OK)
    void update(@RequestBody() @Valid() final QuestionCommandDto questionCommandDto, @PathVariable(name = "questionId") final long questionId) {
        questionCommandService.update(questionId, questionCommandDto);
    }


    @DeleteMapping("/{questionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable(name = "questionId") final long questionId) {
        questionCommandService.deleteById(questionId);
    }

    @PostMapping("/{questionId}/answers")
    @ResponseStatus(HttpStatus.CREATED)
    void addAnswer(@PathVariable(name = "questionId")final long questionId, @RequestBody() @Valid() AnswerCommandDto answerCommandDto){
        questionCommandService.addAnswer(questionId, answerCommandDto);
    }

}
