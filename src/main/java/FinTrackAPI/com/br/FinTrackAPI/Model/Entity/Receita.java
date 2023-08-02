package FinTrackAPI.com.br.FinTrackAPI.Model.Entity;

import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "receitas")
@Data
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    public void atualizar(RequestDTO requestDTO) {
        if (requestDTO.descricao() != null || requestDTO.descricao().isBlank()) {
            this.descricao = requestDTO.descricao();
        }
        if (requestDTO.valor() != null) {
            this.valor = requestDTO.valor();
        }
        if (requestDTO.data() != null) {
            this.data = requestDTO.data();
        }
    }
}
