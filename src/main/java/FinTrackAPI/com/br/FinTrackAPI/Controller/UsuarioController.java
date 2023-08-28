package FinTrackAPI.com.br.FinTrackAPI.Controller;

import FinTrackAPI.com.br.FinTrackAPI.Configuration.SecurityConfigurations;
import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTODadosCadastroUsuario;
import FinTrackAPI.com.br.FinTrackAPI.Service.UsuarioServico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioServico servico;

    @PostMapping
    public ResponseEntity salvar(@RequestBody RequestDTODadosCadastroUsuario dados) {
        servico.salvarUsuarioComSenhaConvertida(dados);
        return ResponseEntity.ok().build();
    }
}
