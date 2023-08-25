package FinTrackAPI.com.br.FinTrackAPI.Controller;

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
@SpringBootTest
class ReceitaControllerDeletarTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ReceitaRepository repository;

    @Test
    void deletar() throws Exception {

        //var
        var receitaSalva = repository.save(new Receita("celular", new BigDecimal("12.00"), LocalDate.now(), Categoria.LAZER));

        //mock
        var response = mvc.perform(
                        MockMvcRequestBuilders.delete("/receitas/" + receitaSalva.getId())
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn()
                .getResponse();

        assertThat(repository.findById(receitaSalva.getId())).isEqualTo(Optional.empty());

    }
}