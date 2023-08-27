package FinTrackAPI.com.br.FinTrackAPI.Controller;

import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import FinTrackAPI.com.br.FinTrackAPI.Service.DespesaServico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/despesas")
@SecurityRequirement(name = "bearer-key")
public class DespesaController {

    @Autowired
    private DespesaServico despesaServico;

    @PostMapping
    public ResponseEntity<ResponseDTO> salvar(@RequestBody @Valid RequestDTO requestDTO, UriComponentsBuilder uriBuilder) throws SQLIntegrityConstraintViolationException {
        var receita = despesaServico.salvar(requestDTO);
        var uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseDTO(receita));
    }

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> listagem(@RequestParam(value = "categoria", required = false) Categoria categoria) {
        List<ResponseDTO> lista = (categoria != null) ? despesaServico.listagemPorCategoria(categoria) : despesaServico.listagem();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<List<ResponseDTO>> listagem(
            @PathVariable Integer ano,
            @PathVariable Integer mes) {
        return ResponseEntity.ok().body(despesaServico.listagemPorData(ano, mes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> detalhar(@PathVariable long id) {
        return ResponseEntity.ok().body(despesaServico.detalhar(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid RequestDTO requestDTO) {
        return ResponseEntity.ok().body(despesaServico.atualizar(id, requestDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        despesaServico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
