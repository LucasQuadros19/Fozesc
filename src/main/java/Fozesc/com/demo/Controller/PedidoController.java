package Fozesc.com.demo.Controller;
import Fozesc.com.demo.Entity.Pedido;
import Fozesc.com.demo.Repository.PedidoRepository;
import Fozesc.com.demo.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/pedido")
public class PedidoController {
    @Autowired
    private PedidoRepository Repository;

    @Autowired
    private PedidoService Service;

    @GetMapping("/lista")
    public ResponseEntity<List<Pedido>> lista(){
        List<Pedido> listartudo = Service.listartudo();
        return ResponseEntity.ok(listartudo);
    }
    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id){
        Pedido listarid = Repository.findById(id).orElse(null);
        return listarid == null
                ? ResponseEntity.badRequest().body(" <<ERRO>>: valor nao encontrado.")
                : ResponseEntity.ok(listarid);
    }
    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<Pedido>> listaAtivo(@PathVariable boolean ativo) {
        List<Pedido> listarAtivo = Repository.findByAtivo(ativo);
        return ResponseEntity.ok(listarAtivo);
    }
    @PostMapping("/cadastrar/price")
    public ResponseEntity<String> TabelaPrice(@RequestBody Pedido cadastro) {
        try {
            Pedido pedidoComJuros = Service.calcularTabelaPrice(cadastro);
            return ResponseEntity.ok("Operação concluída com sucesso!");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    /*
    @GetMapping("/lista/cliente/{id}")
    public ResponseEntity<?> listacliente(@PathVariable(value = "id") Long id){
        List<Pedido> pedidosDoCliente = Repository.findByClienteId(id);

        if (!pedidosDoCliente.isEmpty()) {
            return ResponseEntity.ok(pedidosDoCliente);
        } else {
            return ResponseEntity.badRequest().body("Nenhum pedido encontrado para o cliente.");
        }
    }

    @PostMapping("/cadastrar/simples")
    public ResponseEntity<Pedido> cadastrarSimples(@RequestBody Pedido cadastro) {
        try {
            Pedido pedidoComJuros = Service.pedidoMensalSimples(cadastro);
            return ResponseEntity.ok(pedidoComJuros);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/cadastrar/composto")
    public ResponseEntity<Pedido> cadastrarComposto(@RequestBody Pedido cadastro) {
        try {
            Pedido pedidoComJuros = Service.pedidoMensalComposto(cadastro);
            return ResponseEntity.ok(pedidoComJuros);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
         //   return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/cadastrar/diario")
    public ResponseEntity<?> cadastrarDiario(@RequestBody Pedido cadastro) {
        try {
            Pedido pedidoComJuros = Service.pedidodiarioSimples(cadastro);
            return ResponseEntity.ok("Cadastro feito com sucesso. Valor total: " + pedidoComJuros.getTotal()+
                                            " Valor="+pedidoComJuros.getValorLiquido()+" periodo de ="+pedidoComJuros.getQuantidade());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        }
    }

 */
    /*
    @Transactional(rollbackFor = Exception.class)
    public Pedido calculoMensal(Pedido pedido) {
        Double valorInicial = pedido.getValorDoc(); // Obtém o valor inicial do pedido
        Integer jurosInt = pedido.getJuros().intValue(); // Obtém o valor dos juros do pedido como um número inteiro
        Integer quantidade = pedido.getQuantidade(); // Obtém a quantidade do pedido
        Double jurosDecimal = jurosInt / 100.0;
        Double resultadoDivisao = valorInicial / quantidade;
        Double valorLiquido = resultadoDivisao + (resultadoDivisao * jurosDecimal);

        pedido.setValorLiquido(valorLiquido);
        pedido.setTotal(valorLiquido); // Neste exemplo, o total foi considerado o mesmo que o valor líquido

        return Repository.save(pedido); // Salva o pedido com os valores atualizados e retorna o pedido criado
    }

     */
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody Pedido cadastro){
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
        Optional<Pedido> deletarId = Repository.findById(id);
        if (deletarId.isPresent()) {
            Repository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar( @PathVariable Long id, @RequestBody Pedido atualizarId) {
        try {
            this.Service.atualizar(id, atualizarId);
            return ResponseEntity.ok().body(" atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
