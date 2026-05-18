package br.com.fiap.calorias.controller;

import br.com.fiap.calorias.dto.UsuarioCadastroDTO;
import br.com.fiap.calorias.dto.UsuarioExibicaoDTO;
import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Page<UsuarioExibicaoDTO> listarTodos(
            @PageableDefault(size = 2, page = 0) Pageable paginacao
    ) {
        return usuarioService.listarTodos(paginacao);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioExibicaoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
    }

    @GetMapping(value = "/usuarios", params = "dominio")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibicaoDTO> listarPorDominioEmail(
            @RequestParam String dominio,
            @PageableDefault(size = 2, page = 0) Pageable paginacao
    ){
        return usuarioService.listarPorDominioEmail(dominio, paginacao);
    }

    @PutMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(
            @Valid
            @RequestBody Usuario usuario
    ) {
        return usuarioService.atualizar(usuario);
    }

    @RequestMapping(value = "/usuarios", params = "email")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDTO buscarPorEmail(@RequestParam String email) {
        return usuarioService.buscarPorEmail(email);
    }
}
