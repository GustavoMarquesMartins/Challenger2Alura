package FinTrackAPI.com.br.FinTrackAPI.Controller;

import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Service.ReceitaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaServico receitaServico;

    @PostMapping
    public ResponseEntity<ResponseDTO> salvar(@RequestBody RequestDTO requestDTO, UriComponentsBuilder uriBuilder) throws SQLIntegrityConstraintViolationException {
        var receita = receitaServico.salvar(requestDTO);
        var uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseDTO(receita));
    }

    @GetMapping
    public ResponseEntity<Page<ResponseDTO>> listagem(@PageableDefault(size = 10, sort = {"valor"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok().body(receitaServico.listagem(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> detalhar(@PathVariable long id) {
        return ResponseEntity.ok().body(receitaServico.detalhar(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDTO> atualizar(@PathVariable Long id, @RequestBody RequestDTO requestDTO) {
        return ResponseEntity.ok().body(receitaServico.atualizar(id, requestDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        receitaServico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
