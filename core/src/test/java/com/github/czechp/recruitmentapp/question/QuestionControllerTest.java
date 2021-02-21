package com.github.czechp.recruitmentapp.question;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.czechp.recruitmentapp.RecruitmentAppApplication;
import com.github.czechp.recruitmentapp.question.dto.QuestionCommandDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

    @MockBean
    QuestionRepository questionRepository;

    @Autowired()
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test()
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
    void update() throws Exception {
        //given
        QuestionCommandDto requestBody = new QuestionCommandDto(1L, "Question testing content", Category.ELECTRIC);
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
    void updateTest_entityNotFound() throws Exception {
        //given
        QuestionCommandDto requestBody = new QuestionCommandDto(1L, "Question testing content", Category.ELECTRIC);
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

    private String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

}
