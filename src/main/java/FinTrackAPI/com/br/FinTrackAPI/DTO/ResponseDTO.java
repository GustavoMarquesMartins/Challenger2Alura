package FinTrackAPI.com.br.FinTrackAPI.DTO;

import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Despesa;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Receita;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ResponseDTO(String descricao, BigDecimal valor, @JsonFormat(pattern = "dd/MM/yyyy") LocalDate data) {
    public ResponseDTO(Receita receita) {
        this(receita.getDescricao(), receita.getValor(), receita.getData());
    }
    public ResponseDTO(Despesa despesa) {
        this(despesa.getDescricao(), despesa.getValor(), despesa.getData());
    }
}
