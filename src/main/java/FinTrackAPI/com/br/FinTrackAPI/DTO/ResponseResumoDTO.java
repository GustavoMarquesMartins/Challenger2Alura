package FinTrackAPI.com.br.FinTrackAPI.DTO;

import java.math.BigDecimal;
import java.util.List;

public record ResponseResumoDTO(BigDecimal somaTodasReceitas, BigDecimal somaTodasDespesas, BigDecimal saldoFinal,
                                List<Object[]> SomaTodosGastosAgrupadosPorCategorial) {
}
