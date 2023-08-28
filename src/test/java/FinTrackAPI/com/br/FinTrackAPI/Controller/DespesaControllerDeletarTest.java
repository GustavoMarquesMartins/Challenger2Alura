package FinTrackAPI.com.br.FinTrackAPI.Controller;

import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Despesa;
import FinTrackAPI.com.br.FinTrackAPI.Model.Repository.DespesasRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
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
class DespesaControllerDeletarTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DespesasRepository repository;

    @Test
    @WithMockUser
    void deletar() throws Exception {

        //var
        var despesaSalva = repository.save(new Despesa(null,"celular", new BigDecimal("12.00"), LocalDate.now(), Categoria.LAZER));

        //mock
        var response = mvc.perform(
                        MockMvcRequestBuilders.delete("/despesas/" + despesaSalva.getId())
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn()
                .getResponse();

        assertThat(repository.findById(despesaSalva.getId())).isEqualTo(Optional.empty());

    }
}