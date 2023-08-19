package FinTrackAPI.com.br.FinTrackAPI.DTO;

import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RequestDTO(@NotBlank String descricao, @NotNull BigDecimal valor,
                         @NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy") LocalDate data,
                         Categoria categoria) {
    public RequestDTO(RequestDTO requestDTO) {
        this(requestDTO.descricao, requestDTO.valor, requestDTO.data, Categoria.OUTRAS);
    }
}
