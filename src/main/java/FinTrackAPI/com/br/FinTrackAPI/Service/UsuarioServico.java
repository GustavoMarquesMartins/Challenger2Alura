package FinTrackAPI.com.br.FinTrackAPI.Service;

import FinTrackAPI.com.br.FinTrackAPI.Configuration.SecurityConfigurations;
import FinTrackAPI.com.br.FinTrackAPI.DTO.RequestDTODadosCadastroUsuario;
import FinTrackAPI.com.br.FinTrackAPI.Model.Entity.Usuario;
import FinTrackAPI.com.br.FinTrackAPI.Model.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private SecurityConfigurations securityConfigurations;

    public void salvarUsuarioComSenhaConvertida(RequestDTODadosCadastroUsuario dados) {
        var senhaCriptografada = securityConfigurations.passwordEncoder().encode(dados.senha());
        repository.save(new Usuario(null, dados.login(), senhaCriptografada));
    }
}
