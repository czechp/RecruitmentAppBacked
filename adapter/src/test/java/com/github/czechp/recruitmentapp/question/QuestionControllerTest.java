package com.github.czechp.recruitmentapp.question;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootConfiguration
@SpringBootTest()
@AutoConfigureMockMvc()
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
class QuestionControllerTest {
    private static final String URL = "/api/questions";

    @MockBean()
    QuestionCommandRepository questionCommandRepository;

    @MockBean()
    QuestionQueryRepository questionQueryRepository;

    @Autowired()
    MockMvc mockMvc;

    @Test()
    void findAll_Test() throws Exception {
        //given
        //when
        when(questionQueryRepository.findAll()).thenReturn(
                Arrays.asList(
                        new QuestionDto() {
                            @Override
                            public long getId() {
                                return 1L;
                            }

                            @Override
                            public String getContent() {
                                return "Some content";
                            }

                            @Override
                            public Category getCategory() {
                                return Category.PLC;
                            }
                        }
                )
        );
        //then

        mockMvc.perform(get(URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}