package cr.ac.una.progra4examen1.service;

import cr.ac.una.progra4examen1.model.MisDocumento;
import cr.ac.una.progra4examen1.model.Documento;
import cr.ac.una.progra4examen1.model.Usuario;
import cr.ac.una.progra4examen1.repository.MisDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MisDocumentoService {

    private final MisDocumentoRepository misDocumentoRepository;

    public MisDocumentoService(MisDocumentoRepository misDocumentoRepository) {
        this.misDocumentoRepository = misDocumentoRepository;
    }

    public MisDocumento guardar(MisDocumento misDocumento) {
        return misDocumentoRepository.save(misDocumento);
    }

    public List<MisDocumento> obtenerPorUsuario(Usuario usuario) {
        return misDocumentoRepository.findByUsuario(usuario);
    }

    public Optional<MisDocumento> buscarPorUsuarioYDocumento(Usuario usuario, Documento documento) {
        return misDocumentoRepository.findByUsuarioAndDocumento(usuario, documento);
    }

    public void eliminarPorId(Integer id) {
        misDocumentoRepository.deleteById(id);
    }
}
