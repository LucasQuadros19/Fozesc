package Fozesc.com.demo.Controller;

import Fozesc.com.demo.Entity.Mensagem;
import Fozesc.com.demo.Entity.Pedido;
import Fozesc.com.demo.Entity.Pessoa;
import Fozesc.com.demo.Repository.PedidoRepository;
import Fozesc.com.demo.Repository.PessoaRepository;
import Fozesc.com.demo.Service.PedidoService;
import Fozesc.com.demo.Service.PessoaService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/pessoa")
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaController {

    @Autowired
    private PessoaRepository Repository;
    @Autowired
    private PessoaService Service;

    @GetMapping("/lista")
    public ResponseEntity<List<Pessoa>> lista(){
        List<Pessoa> listartudo = Service.listartudo();
        return ResponseEntity.ok(listartudo);
    }
    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id){
        Pessoa listarid = Repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body(" <<ERRO>>: valor nao encontrado.")
                : ResponseEntity.ok(listarid);
    }
    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<Pessoa>> listaAtivo(@PathVariable boolean ativo) {
        List<Pessoa> listarAtivo = Repository.findByAtivo(ativo);
        return ResponseEntity.ok(listarAtivo);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Mensagem> cadastrar(@RequestBody Pessoa cadastro){
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
        Optional<Pessoa> deletarId = Repository.findById(id);
        if (deletarId.isPresent()) {
            Repository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar( @PathVariable Long id, @RequestBody Pessoa atualizarId) {
        try {
            this.Service.atualizar(id, atualizarId);
            return ResponseEntity.ok().body(" atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
