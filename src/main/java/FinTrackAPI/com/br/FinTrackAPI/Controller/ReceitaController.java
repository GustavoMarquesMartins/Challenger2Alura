package FinTrackAPI.com.br.FinTrackAPI.Controller;

import FinTrackAPI.com.br.FinTrackAPI.DTO.ReceitaDadosCadastroDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ReceitaDadosResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Service.ReceitaServico;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaServico receitaServico;

    @PostMapping
    public ResponseEntity salvar(@RequestBody ReceitaDadosCadastroDTO receitaDadosCadastroDTO, UriComponentsBuilder uriBuilder) throws SQLIntegrityConstraintViolationException {
        var receita = receitaServico.salvar(receitaDadosCadastroDTO);
        var uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.created(uri).body(new ReceitaDadosResponseDTO(receita));
    }
}
