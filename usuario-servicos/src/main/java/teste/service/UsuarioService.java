package teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teste.controller.response.UsuarioResponse;
import teste.modelo.Usuario;
import teste.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioResponse getInfoById(Integer id) {

        Optional<Usuario> usuarioOpt = usuarioRepository.getById(id);
        if(!usuarioOpt.isPresent()) {
            throw new RuntimeException("Recurso não encontrado");
        }
        Usuario usuario = usuarioOpt.get();

        UsuarioResponse response = new UsuarioResponse();
        response.setNome(usuario.getNome());

        return response;
    }
}