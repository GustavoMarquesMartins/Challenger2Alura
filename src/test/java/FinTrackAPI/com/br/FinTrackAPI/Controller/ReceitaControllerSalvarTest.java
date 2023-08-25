package FinTrackAPI.com.br.FinTrackAPI.Controller;

import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Receita;
import FinTrackAPI.com.br.FinTrackAPI.Service.ReceitaServico;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class ReceitaControllerSalvarTest {

    @MockBean
    private ReceitaServico servico;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<RequestDTO> requestDTO;
    @Autowired
    private JacksonTester<ResponseDTO> responseDTO;

    private ReceitaController controller;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.controller = new ReceitaController(servico);
    }

    @Test
    @DisplayName("quando passado dados validos deveria ser retornado uri codigo http 201 e objeto response")
    void salvar() throws Exception {

        //Data
        var data = LocalDate.now().plusDays(2);

        //when
        Mockito.when(servico.salvar(any())).thenReturn(new Receita(1L, "teste", new BigDecimal("1000.00"), data, Categoria.LAZER));

        //fazendo o test
        var response = mvc.perform(
                MockMvcRequestBuilders.post("/receitas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestDTO.write(new RequestDTO("teste", new BigDecimal("1000.00"), data, Categoria.LAZER)).getJson()
                        )
        ).andReturn().getResponse();

        //assert http 201
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        //assert json
        var jsonFinalEsperado = responseDTO.write(new ResponseDTO("teste", new BigDecimal("1000.00"), data, Categoria.LAZER)).getJson();
        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonFinalEsperado);

        //assert uri
        var uri = response.getHeader("Location");
        Assertions.assertThat(uri).isEqualTo("http://localhost/receitas/1");
    }
}