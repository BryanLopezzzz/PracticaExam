package cr.ac.una.progra4examen1.controller;

import cr.ac.una.progra4examen1.model.Documento;
import cr.ac.una.progra4examen1.model.MisDocumento;
import cr.ac.una.progra4examen1.model.TipoDocumento;
import cr.ac.una.progra4examen1.model.Usuario;
import cr.ac.una.progra4examen1.service.DocumentoService;
import cr.ac.una.progra4examen1.service.MisDocumentoService;
import cr.ac.una.progra4examen1.service.TipoDocumentoService;
import cr.ac.una.progra4examen1.service.UsuarioService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping("/documentos")
public class DocumentoController {

    private final MisDocumentoService  misDocumentoService;
    private final DocumentoService     documentoService;
    private final UsuarioService       usuarioService;
    private final TipoDocumentoService tipoDocumentoService;

    public DocumentoController(MisDocumentoService misDocumentoService,
                               DocumentoService documentoService,
                               UsuarioService usuarioService,
                               TipoDocumentoService tipoDocumentoService) {
        this.misDocumentoService  = misDocumentoService;
        this.documentoService     = documentoService;
        this.usuarioService       = usuarioService;
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @GetMapping("/show")
    public String verDocumentos(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam(name = "tipo", required = false) String tipoCodigo,
                                Model model) {

        Usuario usuario = usuarioService.buscarPorId(userDetails.getUsername());

        List<MisDocumento> misDocumentos = misDocumentoService.obtenerPorUsuario(usuario);

        double total = misDocumentos.stream()
                .mapToDouble(md -> (md.getDocumento().getMonto()
                        + md.getDocumento().getTimbres())
                        * md.getCantidad())
                .sum();

        List<TipoDocumento> tipos = tipoDocumentoService.listarTodos();

        List<Documento> documentosFiltrados = new ArrayList<>();
        if (tipoCodigo != null && !tipoCodigo.isEmpty()) {
            documentosFiltrados = documentoService.buscarPorTipo(tipoCodigo);
        }

        model.addAttribute("tipos",               tipos);
        model.addAttribute("selectedTipo",         tipoCodigo);
        model.addAttribute("documentosFiltrados",  documentosFiltrados);
        model.addAttribute("misDocumentos",        misDocumentos);
        model.addAttribute("total",                total);
        model.addAttribute("username",             userDetails.getUsername());

        return "documentos";
    }

    @PostMapping("/agregar")
    public String agregarDocumento(@RequestParam("docId")      String docId,
                                   @RequestParam("tipoActual") String tipoCodigo,
                                   @AuthenticationPrincipal UserDetails userDetails) {

        Usuario  usuario  = usuarioService.buscarPorId(userDetails.getUsername());
        Documento documento = documentoService.buscarPorId(docId);

        Optional<MisDocumento> existente =
                misDocumentoService.buscarPorUsuarioYDocumento(usuario, documento);

        if (existente.isPresent()) {
            MisDocumento md = existente.get();
            md.setCantidad(md.getCantidad() + 1);
            misDocumentoService.guardar(md);
        } else {
            MisDocumento nuevo = new MisDocumento(usuario, documento, 1);
            misDocumentoService.guardar(nuevo);
        }
        return "redirect:/documentos/show?tipo=" + tipoCodigo;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDocumento(@PathVariable Integer id) {
        misDocumentoService.eliminarPorId(id);
        return "redirect:/documentos/show";
    }

    @GetMapping
    public String redirigir() {
        return "redirect:/documentos/show";
    }
}
