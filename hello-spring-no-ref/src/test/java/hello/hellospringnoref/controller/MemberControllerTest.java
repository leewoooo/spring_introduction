package hello.hellospringnoref.controller;

import hello.hellospringnoref.repository.DataJpaMemberRepository;
import hello.hellospringnoref.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private DataJpaMemberRepository memberRepository;

    @Test
    void saveMember() throws Exception {
        //given
        String url = "/api/v1/members";
        String givenContent = "{ \"name\" = \"foobar\"}";

        //when
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(givenContent)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("foobar"))
                .andDo(print());
    }

    @Test
    void findOneById() {
    }

    @Test
    void findAllMembers() {
    }
}