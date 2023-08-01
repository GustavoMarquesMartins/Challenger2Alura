package FinTrackAPI.com.br.FinTrackAPI.DTO;

import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Receita;
import FinTrackAPI.com.br.FinTrackAPI.Service.ReceitaServico;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaDadosResponseDTO(String descricao, BigDecimal valor, LocalDate data) {
    public ReceitaDadosResponseDTO(Receita receita) {
        this(receita.getDescricao(), receita.getValor(), receita.getData());
    }
}
