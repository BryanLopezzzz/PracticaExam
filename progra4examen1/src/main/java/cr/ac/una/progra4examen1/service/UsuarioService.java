package cr.ac.una.progra4examen1.service;

import cr.ac.una.progra4examen1.model.Usuario;
import cr.ac.una.progra4examen1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario buscarPorId(String id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
