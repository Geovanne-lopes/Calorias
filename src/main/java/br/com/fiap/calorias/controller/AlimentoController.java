package br.com.fiap.calorias.controller;

import br.com.fiap.calorias.dto.AlimentoCadastroDTO;
import br.com.fiap.calorias.dto.AlimentoExibicaoDTO;
import br.com.fiap.calorias.service.AlimentoService;
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
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @PostMapping("/alimentos")
    @ResponseStatus(HttpStatus.CREATED)
    public AlimentoExibicaoDTO salvar(
            @Valid @RequestBody AlimentoCadastroDTO alimento){
        return alimentoService.salvarAlimento(alimento);
    }

    @GetMapping("/alimentos")
    @ResponseStatus(HttpStatus.OK)
    public Page<AlimentoExibicaoDTO> listarTodos(
            @PageableDefault(size = 20, page = 0) Pageable paginacao
            ){
        return alimentoService.listarTodos(paginacao);
    }

    @GetMapping("/alimentos/{alimentoId}")
    public ResponseEntity<AlimentoExibicaoDTO> buscarPorId(
            @PathVariable Long alimentoId){
        return ResponseEntity.ok(alimentoService.buscarPorId(alimentoId));
    }

    @DeleteMapping("/alimentos/{alimentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long alimentoId){
        alimentoService.excluir(alimentoId);
    }

    @PutMapping("/alimentos")
    public ResponseEntity<AlimentoExibicaoDTO> atualizar(
            @Valid @RequestBody AlimentoCadastroDTO alimentoDTO){
        return ResponseEntity.ok(alimentoService.atualizar(alimentoDTO));
    }

    // http://localhost:8080/api/alimentos?nome=abacate
    @GetMapping(value = "/alimentos", params = "nome")
    public ResponseEntity<AlimentoExibicaoDTO> buscarPorNome(String nome){

        try {
            return ResponseEntity.ok(alimentoService.buscarPorNome(nome));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/alimentos", params = {"caloriasMinima", "caloriasMaxima"})
    @ResponseStatus(HttpStatus.OK)
    public Page<AlimentoExibicaoDTO> listarAlimentosPorFaixaDeCalorias(
            @RequestParam Double caloriasMinima,
            @RequestParam Double caloriasMaxima,
            @PageableDefault(size = 2, page = 0) Pageable paginacao
    ){
        return alimentoService.listarAlimentosPorFaixaDeCalorias(caloriasMinima, caloriasMaxima, paginacao);
    }

    @GetMapping(value = "/alimentos", params = "prefixo")
    @ResponseStatus(HttpStatus.OK)
    public Page<AlimentoExibicaoDTO> listarPorNomeComecandoCom(
            @RequestParam String prefixo,
            @PageableDefault(size = 2, page = 0) Pageable paginacao
    ){
        return alimentoService.listarPorNomeComecandoCom(prefixo, paginacao);
    }

    @GetMapping(value = "/alimentos", params = "gorduraMaxima")
    @ResponseStatus(HttpStatus.OK)
    public Page<AlimentoExibicaoDTO> listarPorGorduraInferiorA(
            @RequestParam Double gorduraMaxima,
            @PageableDefault(size = 2, page = 0) Pageable paginacao
    ){
        return alimentoService.listarPorGorduraInferiorA(gorduraMaxima, paginacao);
    }

    @RequestMapping(value = "/alimentos", params ="caloriasMenorQue")
    @ResponseStatus(HttpStatus.OK)
    public Page<AlimentoExibicaoDTO> listarTotalCaloriasMenorQue(
            @RequestParam Double caloriasMenorQue,
            @PageableDefault(size = 2, page = 0) Pageable paginacao
    ){
        return  alimentoService.listarTotalCaloriasMenorQue(caloriasMenorQue, paginacao);
    }
}
