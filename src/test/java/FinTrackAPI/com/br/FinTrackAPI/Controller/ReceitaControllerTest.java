package FinTrackAPI.com.br.FinTrackAPI.Controller;


import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Receita;
import FinTrackAPI.com.br.FinTrackAPI.Service.ReceitaServico;
import net.bytebuddy.asm.Advice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class ReceitaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<RequestDTO> requestDTOJson;

    @Autowired
    private JacksonTester<ResponseDTO> responseDTOJson;

    @MockBean
    private ReceitaServico receitaServico;

    @Test
    @DisplayName("deveria retornar codigo http 201 e o objeto em json da receita criada")
    void salvarAssert1() throws Exception {

        //definindo var
        LocalDate data = LocalDate.of(2023, 8, 1);
        var requestDTO = new RequestDTO("despesas", new BigDecimal("12.00"), data, Categoria.LAZER);
        var receita = new Receita(null, "despesas", new BigDecimal("12.00"), data, Categoria.LAZER);
        var responseJsonEsperado = new ResponseDTO("despesas", new BigDecimal("12.00"), data, Categoria.LAZER);

        //mod metodo
        Mockito.when(receitaServico.salvar(Mockito.any())).thenReturn(receita);

        //realiazando a simulacao
        var response = mvc.perform(
                MockMvcRequestBuilders.post("/receitas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestDTOJson.write(requestDTO)
                                .getJson())
        ).andReturn().getResponse();

        //fazendo a primeira assertiva
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        //fazendo a segunda assertiva
        var responseJsonEsperada = responseDTOJson.write(responseJsonEsperado).getJson();
        assertThat(response.getContentAsString()).isEqualTo(responseJsonEsperada);
    }

    @Test
    @DisplayName("quando a categoria nao for passada deveria retornar categoria como OUTRAS")
    void salvarAssert2() throws Exception {

        //definindo var
        LocalDate data = LocalDate.of(2023, 8, 1);
        var requestDTO = new RequestDTO("despesas", new BigDecimal("12.00"), data, null);
        var receita = new Receita(null, "despesas", new BigDecimal("12.00"), data, Categoria.LAZER);
        var responseJsonEsperado = new ResponseDTO("despesas", new BigDecimal("12.00"), data, Categoria.LAZER);

        //mod metodo
        Mockito.when(receitaServico.salvar(Mockito.any())).thenReturn(receita);

        //realiazando a simulacao
        var response = mvc.perform(
                MockMvcRequestBuilders.post("/receitas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestDTOJson.write(requestDTO)
                                .getJson())
        ).andReturn().getResponse();

        //fazendo a segunda assertiva
        var responseJsonEsperada = responseDTOJson.write(responseJsonEsperado).getJson();
        assertThat(response.getContentAsString()).isEqualTo(responseJsonEsperada);
    }
}