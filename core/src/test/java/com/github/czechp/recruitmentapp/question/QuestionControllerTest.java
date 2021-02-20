package com.github.czechp.recruitmentapp.question;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc()
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
                        QuestionCommandService.toDto(new Question("Some content 1", Category.PLC)),
                        QuestionCommandService.toDto(new Question("Some content 2", Category.ELECTRIC))
                )
        );
        //then
        mockMvc.perform(get(URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].content").value("Some content 1"))
                .andExpect(jsonPath("$[0].category").value("PLC"))
                .andExpect(status().isOk());
    }

    private String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

}
