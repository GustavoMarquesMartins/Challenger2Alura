package FinTrackAPI.com.br.FinTrackAPI.Service;

import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseResumoDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Repository.DespesasRepository;
import FinTrackAPI.com.br.FinTrackAPI.Model.Repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ResumoServico {

    @Autowired
    private ReceitaRepository receitaRepository;
    @Autowired
    private DespesasRepository despesasRepository;

    public ResponseResumoDTO listarSomaTodasReceitas(Integer ano, Integer mes) {
        var somaTodasReceitas = receitaRepository.somaTotalReceitas(ano, mes);
        System.out.println(somaTodasReceitas);
        var somaTodasDespesas = despesasRepository.somaTotalDepesas(ano, mes);
        System.out.println(somaTodasDespesas);
        var saldoFinal = somaTodasReceitas.subtract(somaTodasDespesas).setScale(2, RoundingMode.HALF_UP);
        List<Object[]> somaTodosGastosAgrupadosPorCategoria = despesasRepository.valorGastoPorCategoria(ano, mes);
        return new ResponseResumoDTO(somaTodasReceitas, somaTodasDespesas, saldoFinal, somaTodosGastosAgrupadosPorCategoria);
    }
}
