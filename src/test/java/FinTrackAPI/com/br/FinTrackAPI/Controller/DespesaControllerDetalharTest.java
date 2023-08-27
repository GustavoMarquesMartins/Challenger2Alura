package FinTrackAPI.com.br.FinTrackAPI.Controller;

import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Despesa;
import FinTrackAPI.com.br.FinTrackAPI.Model.Repository.DespesasRepository;
import FinTrackAPI.com.br.FinTrackAPI.Service.DespesaServico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@SpringBootTest
class DespesaControllerDetalharTest {

    @Autowired
    private DespesaServico servico;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ResponseDTO> responseJson;

    @Autowired
    private DespesasRepository repository;

    @Test
    void detalhar() throws Exception {

        //var
        var despesaSalva = repository.save(new Despesa(null, "celular", new BigDecimal("12.00"), LocalDate.now(), Categoria.LAZER));
        var jsonEsperado = responseJson.write(servico.detalhar(despesaSalva.getId()));

        //mock
        var response = mvc.perform(
                        MockMvcRequestBuilders.get("/despesas/" + despesaSalva.getId())
                ).andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado.getJson());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}