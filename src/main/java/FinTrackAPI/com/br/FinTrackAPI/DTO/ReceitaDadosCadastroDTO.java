package FinTrackAPI.com.br.FinTrackAPI.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaDadosCadastroDTO(
        @NotBlank
        String descricao,
        @NotNull
        BigDecimal valor,
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data) {
}
