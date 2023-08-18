package Fozesc.com.demo.Controller;
import Fozesc.com.demo.Entity.Historico;
import Fozesc.com.demo.Repository.HistoricoRepository;
import Fozesc.com.demo.Service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/historico")
public class HistoricoController {

    @Autowired
    private HistoricoRepository Repository;
    @Autowired
    private HistoricoService Service;

    @GetMapping("/lista")
    public ResponseEntity<List<Historico>> lista(){
        List<Historico> listartudo = Service.listartudo();
        return ResponseEntity.ok(listartudo);
    }

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id){
        Historico listarid = Repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body(" <<ERRO>>: valor nao encontrado.")
                : ResponseEntity.ok(listarid);
    }



    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<Historico>> listaAtivo(@PathVariable boolean ativo) {
        List<Historico> listarAtivo = Repository.findByAtivo(ativo);
        return ResponseEntity.ok(listarAtivo);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Historico cadastro){
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
        Optional<Historico> deletarId = Repository.findById(id);
        if (deletarId.isPresent()) {
            Repository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar( @PathVariable Long id, @RequestBody Historico atualizarId) {
        try {
            this.Service.atualizar(id, atualizarId);
            return ResponseEntity.ok().body(" atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
