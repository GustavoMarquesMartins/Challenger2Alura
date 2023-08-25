package FinTrackAPI.com.br.FinTrackAPI.Controller;

import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
class ReceitaControllerSalvarTest {

    @Autowired
    private JacksonTester<RequestDTO> jsonRequest;

    @Autowired
    private JacksonTester<ResponseDTO> jsonResponse;

    @Autowired
    private MockMvc mvc;

    @Test
    void salvar() throws Exception {

        //var
        var receitaRequest = jsonRequest.write(new RequestDTO("aCelulara", new BigDecimal("1000.00"), LocalDate.now(), Categoria.LAZER));
        var receitaResponse = jsonResponse.write(new ResponseDTO("aCelulara", new BigDecimal("1000.00"), LocalDate.now(), Categoria.LAZER));

        //mock
        var response = mvc.perform(
                        MockMvcRequestBuilders.post("/receitas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(receitaRequest.getJson())
                ).andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(receitaResponse.getJson());
    }
}