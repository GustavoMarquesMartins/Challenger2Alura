package FinTrackAPI.com.br.FinTrackAPI.DTO;

import jakarta.validation.constraints.NotBlank;

public record RequestDTODadosCadastroUsuario(@NotBlank String login, @NotBlank String senha) {
}
