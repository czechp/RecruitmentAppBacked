package com.github.czechp.recruitmentapp.question;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.czechp.recruitmentapp.RecruitmentAppApplication;
import com.github.czechp.recruitmentapp.question.dto.AnswerCommandDto;
import com.github.czechp.recruitmentapp.question.dto.QuestionCommandDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {RecruitmentAppApplication.class})
@AutoConfigureMockMvc()
@ActiveProfiles(value = {"test"})
class QuestionControllerTest {
    private static final String URL = "/api/questions";

    @MockBean()
    QuestionRepository questionRepository;

    @MockBean()
    AnswerRepository answerRepository;

    @Autowired()
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = "USER")
    void findAll() throws Exception {
        //given
        //when
        when(questionRepository.findAllBy()).thenReturn(
                Arrays.asList(
                        QuestionFactory.poJoToQueryDto(new Question("Some content 1", Category.PLC)),
                        QuestionFactory.poJoToQueryDto(new Question("Some content 2", Category.ELECTRIC))
                )
        );
        //then
        mockMvc.perform(get(URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].content").value("Some content 1"))
                .andExpect(jsonPath("$[0].category").value("PLC"))
                .andExpect(status().isOk());
    }

    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = "USER")
    void update() throws Exception {
        //given
        QuestionCommandDto requestBody = new QuestionCommandDto("Question testing content", Category.ELECTRIC);
        String requestJsonBody = toJson(requestBody);
        long questionId = 1L;
        //when
        when(questionRepository.save(any())).thenReturn(new Question("Question testing content", Category.ELECTRIC));
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(new Question("Question testing content", Category.ELECTRIC)));

        //then
        mockMvc.perform(
                put(URL + "/{questionId}", questionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJsonBody)
        )
                .andExpect(status().isOk());
    }

    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = "USER")
    void updateTest_entityNotFound() throws Exception {
        //given
        QuestionCommandDto requestBody = new QuestionCommandDto("Question testing content", Category.ELECTRIC);
        String requestJsonBody = toJson(requestBody);
        long questionId = 1L;
        //when
        when(questionRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));

        //then
        mockMvc.perform(
                put(URL + "/{questionId}", questionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJsonBody)
        )
                .andExpect(status().isNotFound());
    }

    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = "USER")
    void deleteTest() throws Exception {
        //given
        long questionId = 1L;
        //when
        when(questionRepository.existsById(anyLong())).thenReturn(true);
        //then
        mockMvc.perform(
                delete(URL + "/{questionId}", questionId)
        )
                .andExpect(status().isNoContent());
        verify(questionRepository, times(1)).deleteById(anyLong());
    }


    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = "USER")
    void deleteTest_entityNotFound() throws Exception {
        //given
        long questionId = 1L;
        //when
        when(questionRepository.existsById(anyLong())).thenReturn(false);
        //then
        mockMvc.perform(
                delete(URL + "/{questionId}", questionId)
        )
                .andExpect(status().isNotFound());
    }

    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = "USER")
    void addAnswerTest() throws Exception {
        //given
        long questionId = 1L;
        AnswerCommandDto requestBody = new AnswerCommandDto("Some answer", false);
        String requestJsonBody = toJson(requestBody);
        Question question = new Question("Some question", Category.PLC);
        question.setId(questionId);
        question.addAnswer(new Answer("Exp content", false));
        //when
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        //then
        mockMvc.perform(post(URL + "/{questionId}/answers", questionId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonBody)
        )
                .andExpect(status().isCreated());
    }

    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = "USER")
    void addAnswerTest_MaxAnswersSize() throws Exception {
        //given
        long questionId = 1L;
        AnswerCommandDto requestBody = new AnswerCommandDto("Some answer", false);
        String requestJsonBody = toJson(requestBody);
        Question question = new Question("Some question", Category.PLC);
        question.setId(questionId);
        question.addAnswer(new Answer("Exp content1", true));
        question.addAnswer(new Answer("Exp content2", false));
        question.addAnswer(new Answer("Exp content3", false));
        question.addAnswer(new Answer("Exp content4", false));
        //when
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        //then
        mockMvc.perform(post(URL + "/{questionId}/answers", questionId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonBody)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Question gat maximum numbers of answers"));
    }

    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = "USER")
    void addAnswerTest_CorrectAnswerAlreadyExists() throws Exception {
        //given
        long questionId = 1L;
        AnswerCommandDto requestBody = new AnswerCommandDto("Some answer", true);
        String requestJsonBody = toJson(requestBody);
        Question question = new Question("Some question", Category.PLC);
        question.setId(questionId);
        question.addAnswer(new Answer("Exp content", true));
        //when
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        //then
        mockMvc.perform(post(URL + "/{questionId}/answers", questionId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonBody)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Question got already correct answer"));
    }

    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = "USER")
    void addAnswerTest_atLeastOneCorrectQuestion() throws Exception {
        //given
        long questionId = 1L;
        AnswerCommandDto requestBody = new AnswerCommandDto("Some answer", false);
        String requestJsonBody = toJson(requestBody);
        Question question = new Question("Some question", Category.PLC);
        question.setId(questionId);
        question.addAnswer(new Answer("Exp content1", false));
        question.addAnswer(new Answer("Exp content2", false));
        question.addAnswer(new Answer("Exp content3", false));
        //when
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        //then
        mockMvc.perform(post(URL + "/{questionId}/answers", questionId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJsonBody)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("At least one answer gotta be correct"));
    }

    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = "USER")
    void deleteAnswerByIdTest() throws Exception {
        //given
        long answerId = 1L;
        Answer answer = new Answer("Some content", true);
        answer.setQuestion(new Question("Some question content", Category.PLC));
        //when
        when(answerRepository.findById(anyLong())).thenReturn(Optional.of(answer));
        //then
        mockMvc.perform(delete(URL + "/answers/{answerId}", answerId))
                .andExpect(status().isNoContent());
    }

    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = "USER")
    void deleteAnswerByIdTest_AnswerNotFound() throws Exception {
        //given
        long answerId = 1L;
        //when
        when(answerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        //then
        mockMvc.perform(delete(URL + "/answers/{answerId}", answerId))
                .andExpect(status().isNotFound());
    }


    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = {"SUPERUSER", "ADMIN"})
    void confirmQuestionTest() throws Exception {
        //given
        long questionId = 1L;
        boolean confirmation = true;
        Question question = new Question("Some content", Category.PLC);
        Arrays.asList(
                new Answer("Answer 1", true),
                new Answer("Answer 2", false),
                new Answer("Answer 3", false),
                new Answer("Answer 4", false)
        ).stream()
                .forEach(answer -> question.addAnswer(answer));
        //when
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        //then
        mockMvc.perform(patch(URL + "/{questionId}", questionId)
                .param("confirmation", "true"))
                .andExpect(status().isOk());
    }


    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = "USER")
    void confirmQuestionTest_NotFound() throws Exception {
        //given
        long questionId = 1L;
        boolean confirmation = true;

        //when
        when(questionRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        //then
        mockMvc.perform(patch(URL + "/{questionId}", questionId)
                .param("confirmation", "true"))
                .andExpect(status().isNotFound());
    }


    @Test()
    @WithMockUser(username = "user123", password = "user123", roles = "USER")
    void confirmQuestionTest_IncompleteAnswers() throws Exception {
        //given
        long questionId = 1L;
        boolean confirmation = true;
        Question question = new Question("Some content", Category.PLC);
        Arrays.asList(
                new Answer("Answer 1", true),
                new Answer("Answer 2", false),
                new Answer("Answer 3", false)
        ).stream()
                .forEach(answer -> question.addAnswer(answer));
        //when
        when(questionRepository.findById(anyLong())).thenReturn(Optional.of(question));
        //then
        mockMvc.perform(patch(URL + "/{questionId}", questionId)
                .param("confirmation", "true"))
                .andExpect(status().isBadRequest());
    }

    private String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

}
