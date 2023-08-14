package Fozesc.com.demo.Controller;

import Fozesc.com.demo.Entity.Historico;
import Fozesc.com.demo.Entity.Limite;
import Fozesc.com.demo.Entity.Operacao;
import Fozesc.com.demo.Repository.LimiteRepository;
import Fozesc.com.demo.Repository.OperacaoRepository;
import Fozesc.com.demo.Service.LimiteService;
import Fozesc.com.demo.Service.OperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = ("/api/operacao"))
public class OperacaoController {
    @Autowired
    private OperacaoRepository Repository;
    @Autowired
    private OperacaoService Service;

    @GetMapping("/lista")
    public ResponseEntity<List<Operacao>> lista(){
        List<Operacao> listartudo = Service.listartudo();
        return ResponseEntity.ok(listartudo);
    }
    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id){
        Operacao listarid = Repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body(" <<ERRO>>: valor nao encontrado.")
                : ResponseEntity.ok(listarid);
    }
    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<Operacao>> listaAtivo(@PathVariable boolean ativo) {
        List<Operacao> listarAtivo = Repository.findByAtivo(ativo);
        return ResponseEntity.ok(listarAtivo);
    }
   /* @GetMapping("/lista/cliente/{id}")
    public ResponseEntity<List<Operacao>> parcelas(@PathVariable(value = "id") Long clienteId){
        List<Operacao> listarParcela = Repository.findByClienteId(clienteId);
        return ResponseEntity.ok(listarParcela);
    }

    */

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Operacao cadastro){
        try{
            this.Service.cadastrar(cadastro);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("ERRO:"+e.getMessage());
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Operacao> deletarId = Repository.findById(id);
        if (deletarId.isPresent()) {
            Repository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar( @PathVariable Long id, @RequestBody Operacao atualizarId) {
        try {
            this.Service.atualizar(id, atualizarId);
            return ResponseEntity.ok().body(" atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
