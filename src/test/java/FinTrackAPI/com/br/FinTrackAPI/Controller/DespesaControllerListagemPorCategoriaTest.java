package FinTrackAPI.com.br.FinTrackAPI.Controller;

import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
class DespesaControllerListagemPorCategoriaTest {

    @Autowired
    private JacksonTester<RequestDTO> jsonRequest;

    @Autowired
    private JacksonTester<ResponseDTO> jsonResponse;

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    void listagemPorCategoria() throws Exception {

        //var
        var categoria = "LAZER";

        //mock
        mvc.perform(
                        MockMvcRequestBuilders.get("/despesas")
                                .param("categoria", categoria)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].categoria").value(Matchers.everyItem(Matchers.is(categoria))))
                .andReturn()
                .getResponse();


    }
}