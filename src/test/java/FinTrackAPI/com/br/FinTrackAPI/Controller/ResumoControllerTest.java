package FinTrackAPI.com.br.FinTrackAPI.Controller;

import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseResumoDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Despesa;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Receita;
import FinTrackAPI.com.br.FinTrackAPI.Model.Repository.DespesasRepository;
import FinTrackAPI.com.br.FinTrackAPI.Model.Repository.ReceitaRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
class ResumoControllerTest {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private DespesasRepository despesasRepository;

    @Autowired
    private MockMvc mvc;


    @Test
    void listagem() throws Exception {

        //ano e mes
        var data = getDataAleatoriaQueReceitaEDespesasTenhamEmComun();
        var ano = data.getYear();
        var mes = data.getMonthValue();

        var somaTodasReceitas = receitaRepository.somaTotalReceitas(ano, mes).setScale(1);
        var somaTodasDespesas = despesasRepository.somaTotalDepesas(ano, mes).setScale(1);
        var saldoFinal = somaTodasReceitas.subtract(somaTodasDespesas).setScale(1);

        //mvc
        var response = mvc.perform(
                        MockMvcRequestBuilders.get("/resumo/" + ano + "/" + mes)
                )
                .andExpect(MockMvcResultMatchers.jsonPath("$.somaTodasReceitas").value(somaTodasReceitas))
                .andExpect(MockMvcResultMatchers.jsonPath("$.somaTodasDespesas").value(somaTodasDespesas))
                .andExpect(MockMvcResultMatchers.jsonPath("$.saldoFinal").value(saldoFinal))
                .andExpect(MockMvcResultMatchers.jsonPath("$.SomaTodosGastosAgrupadosPorCategorial").isArray())
                .andReturn().getResponse();
    }

    private LocalDate getDataAleatoriaQueReceitaEDespesasTenhamEmComun() {
        Random random = new Random();
        boolean status = false;
        List<LocalDate> listaReceitaDatas = receitaRepository.findAllByData();
        List<LocalDate> listaDespesasDatas = despesasRepository.findAllByData();
        LocalDate resultadoEsperado = null;
        while (status != true) {
            var posicaoAleatoriaReceita = random.nextInt(listaReceitaDatas.size());
            var posicaoAleatoriaDespesas = random.nextInt(listaDespesasDatas.size());

            LocalDate dataAleatoriaReceita = listaReceitaDatas.get(posicaoAleatoriaReceita);
            LocalDate dataAleatoriaDespesa = listaDespesasDatas.get(posicaoAleatoriaDespesas);

            if (dataAleatoriaReceita.isEqual(dataAleatoriaDespesa)) {
                status = true;
                return dataAleatoriaDespesa;
            }
        }
        return null;
    }
}