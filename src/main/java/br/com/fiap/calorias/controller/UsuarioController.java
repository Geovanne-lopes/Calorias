package br.com.fiap.calorias.controller;

import br.com.fiap.calorias.dto.UsuarioCadastroDTO;
import br.com.fiap.calorias.dto.UsuarioExibicaoDTO;
import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO salvar(
            @Valid
            @RequestBody UsuarioCadastroDTO usuarioCadastroDTO
    ) {
        return usuarioService.salvarUsuario(usuarioCadastroDTO);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDTO> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioExibicaoDTO> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(
            @Valid
            @RequestBody Usuario usuario
    ) {
        return usuarioService.atualizar(usuario);
    }
}
