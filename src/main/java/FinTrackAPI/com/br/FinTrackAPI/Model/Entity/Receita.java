package FinTrackAPI.com.br.FinTrackAPI.Model.Entity;

import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "receitas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

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
        this.categoria = requestDTO.categoria();
    }
    public Receita(String descricao, BigDecimal valor, LocalDate data, Categoria categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }
}
