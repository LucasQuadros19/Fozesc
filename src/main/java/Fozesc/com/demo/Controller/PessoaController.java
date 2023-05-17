package Fozesc.com.demo.Controller;

import Fozesc.com.demo.Entity.Pessoa;
import Fozesc.com.demo.Repository.PessoaRepository;
import Fozesc.com.demo.Service.PessoaService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/api/pessoa")
public class PessoaController {
    @Autowired
    private PessoaRepository repository;
    @Autowired
    private PessoaService service;
    @GetMapping("lista")
    public ResponseEntity<List<Pessoa>> tudo(){
        return ResponseEntity.ok(this.service.listagem());
    }
    @GetMapping("lista/id/{id}")
    public ResponseEntity<?> porId(@PathVariable(value="id") Long id){
        Pessoa listaId = repository.findById(id).orElse(null);
        return listaId == null
                ? ResponseEntity.badRequest().body("->ERRO<- Valor nao encontrado")
                : ResponseEntity.ok(listaId);
    }



}
