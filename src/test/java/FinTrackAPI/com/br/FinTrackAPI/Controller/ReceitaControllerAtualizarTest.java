package FinTrackAPI.com.br.FinTrackAPI.Controller;

import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Receita;
import FinTrackAPI.com.br.FinTrackAPI.Model.Repository.ReceitaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@SpringBootTest
class ReceitaControllerAtualizarTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ReceitaRepository repository;

    @Autowired
    private JacksonTester<RequestDTO> requestDTOJson;

    @Test
    void deletar() throws Exception {

        //var
        var receitaSalva = repository.save(new Receita("celular", new BigDecimal("12.00"), LocalDate.now(), Categoria.LAZER));
        var dadosAtualizarReceita = new RequestDTO("celularAtualizado", new BigDecimal("1000.00"), LocalDate.now().plusDays(2), Categoria.OUTRAS);

        //mock
        var response = mvc.perform(
                        MockMvcRequestBuilders.put("/receitas/" + receitaSalva.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestDTOJson.write(dadosAtualizarReceita).getJson()
                                )
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();

        var jsonEsperado = requestDTOJson.write(dadosAtualizarReceita).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);


    }
}