package FinTrackAPI.com.br.FinTrackAPI.Controller;

import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Receita;
import FinTrackAPI.com.br.FinTrackAPI.Model.Repository.ReceitaRepository;
import FinTrackAPI.com.br.FinTrackAPI.Service.ReceitaServico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
class ReceitaControllerDetalharTest {

    @Autowired
    private ReceitaServico servico;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ResponseDTO> responseJson;

    @Autowired
    private ReceitaRepository repository;
    @Test
    @WithMockUser
    void detalhar() throws Exception {

        //var
        var receitaSalva = repository.save(new Receita("celular", new BigDecimal("12.00"), LocalDate.now(), Categoria.LAZER));
        var jsonEsperado = responseJson.write(servico.detalhar(receitaSalva.getId()));

        //mock
        var response = mvc.perform(
                        MockMvcRequestBuilders.get("/receitas/" + receitaSalva.getId())
                ).andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado.getJson());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}