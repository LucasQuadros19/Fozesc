package Fozesc.com.demo.Controller;

import Fozesc.com.demo.Entity.Mensagem;
import Fozesc.com.demo.Entity.Pessoa;
import Fozesc.com.demo.Entity.Situacao;
import Fozesc.com.demo.Repository.PessoaRepository;
import Fozesc.com.demo.Repository.SituacaoRepository;
import Fozesc.com.demo.Service.PessoaService;
import Fozesc.com.demo.Service.SituacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/situacao")
@CrossOrigin(origins = "http://localhost:4200")
public class SituacaoController {


    @Autowired
    private SituacaoRepository Repository;
    @Autowired
    private SituacaoService Service;

    @GetMapping("/lista")
    public ResponseEntity<List<Situacao>> lista(){
        List<Situacao> listartudo = Service.listartudo();
        return ResponseEntity.ok(listartudo);
    }
    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id){
        Situacao listarid = Repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body(" <<ERRO>>: valor nao encontrado.")
                : ResponseEntity.ok(listarid);
    }
    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<Situacao>> listaAtivo(@PathVariable boolean ativo) {
        List<Situacao> listarAtivo = Repository.findByAtivo(ativo);
        return ResponseEntity.ok(listarAtivo);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Mensagem> cadastrar(@RequestBody Situacao cadastro) {
        try{
            this.Service.cadastrar(cadastro);
            Mensagem mensagem = new Mensagem();
            mensagem.setMensagem("Cadastro feito com sucesso");
            return ResponseEntity.ok(mensagem);
        } catch (DataIntegrityViolationException e) {
            Mensagem mensagem = new Mensagem();
            mensagem.setMensagem("ERRO:"+e.getMessage());
            return ResponseEntity.badRequest().body(mensagem);
        }catch (IllegalArgumentException e) {
            Mensagem mensagem = new Mensagem();
            mensagem.setMensagem("ERRO: " + e.getMessage());
            return ResponseEntity.badRequest().body(mensagem);
        } catch (Exception e) {
            Mensagem mensagem = new Mensagem();
            mensagem.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(mensagem);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Situacao> deletarId = Repository.findById(id);
        if (deletarId.isPresent()) {
            Repository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar( @PathVariable Long id, @RequestBody Situacao atualizarId) {
        try {
            this.Service.atualizar(id, atualizarId);
            return ResponseEntity.ok().body(" atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
