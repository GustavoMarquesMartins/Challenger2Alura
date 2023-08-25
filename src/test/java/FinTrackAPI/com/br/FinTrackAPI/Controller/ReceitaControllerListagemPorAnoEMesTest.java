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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
class ReceitaControllerListagemPorAnoEMesTest {

    @Autowired
    private JacksonTester<RequestDTO> jsonRequest;

    @Autowired
    private JacksonTester<ResponseDTO> jsonResponse;

    @Autowired
    private MockMvc mvc;


    @Test
    public void testMinhaListaComMesmoAnoEMes() throws Exception {
        int ano = 2023;
        int mes = 8;

        String mesAnoParam = String.format("%02d/%04d", mes, ano);

        mvc.perform(MockMvcRequestBuilders.get("/receitas/" + ano + "/" + mes))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].data").value(
                        Matchers.everyItem(Matchers.endsWith(mesAnoParam))));

    }
}