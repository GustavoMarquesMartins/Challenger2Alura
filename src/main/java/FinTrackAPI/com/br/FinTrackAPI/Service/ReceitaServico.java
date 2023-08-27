package FinTrackAPI.com.br.FinTrackAPI.Service;

import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTO;
import FinTrackAPI.com.br.FinTrackAPI.DTO.ResponseDTO;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Categoria;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Receita;
import FinTrackAPI.com.br.FinTrackAPI.Model.Repository.ReceitaRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ReceitaServico {

    private ReceitaRepository receitaRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ReceitaServico(ReceitaRepository receitaRepository, ModelMapper modelMapper) {
        this.receitaRepository = receitaRepository;
        this.modelMapper = modelMapper;
    }

    public Receita salvar(RequestDTO requestDTO) throws SQLIntegrityConstraintViolationException {
        var dto = validarCategoria(requestDTO);
        var receita = receitaRepository.save(modelMapper.map(dto, Receita.class));
        return receita;
    }

    public List<ResponseDTO> listagem() {
        return receitaRepository.findAll().stream().map(ResponseDTO::new).collect(Collectors.toList());
    }

    public List<ResponseDTO> listagemPorCategoria(Categoria categoria) {
        return receitaRepository.findAllByCategoria(categoria).stream().map(ResponseDTO::new).collect(Collectors.toList());
    }

    public ResponseDTO detalhar(long id) {
        var receita = receitaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Receita com o id : \" + id + \" não encontrado\""));
        return new ResponseDTO(receita);
    }

    @Transactional
    public ResponseDTO atualizar(Long id, RequestDTO requestDTO) {
        var receita = receitaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Receita com o id : " + id + " não encontrado"));
        receita.atualizar(requestDTO);
        return new ResponseDTO(receita);
    }

    @Transactional
    public void deletar(Long id) {
        receitaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Receita com o id : " + id + " não encontrado"));
        receitaRepository.deleteById(id);
    }

    public RequestDTO validarCategoria(RequestDTO requestDTO) {
        if (requestDTO.categoria() == null) {
            return new RequestDTO(requestDTO);
        }
        return requestDTO;
    }

    public List<ResponseDTO> listagemPorData(Integer ano, Integer mes) {
        return receitaRepository.findByData(ano, mes).stream().map(receitas -> new ResponseDTO(receitas)).collect(Collectors.toList());
    }
}