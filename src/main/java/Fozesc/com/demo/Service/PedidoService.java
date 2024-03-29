package Fozesc.com.demo.Service;

import Fozesc.com.demo.Entity.Pedido;
import Fozesc.com.demo.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository Repository;
    public List<Pedido> listartudo(){
        return Repository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Pedido cadastrar(Pedido cadastrar) {
        Assert.notNull(cadastrar.getQuantidade(), "Error, campo quantidade vazio");

        if(cadastrar.getCheques() != null){
            for(int i=0; i<cadastrar.getCheques().size(); i++){
                cadastrar.getCheques().get(i).setPedido(cadastrar);
            }
        }
        if(cadastrar.getParcelas() != null){
            for(int i=0; i<cadastrar.getParcelas().size(); i++){
                cadastrar.getParcelas().get(i).setPedido(cadastrar);
            }
        }

        //  for(int i=0;i<pedido.getParcelas().size();i++){
       //     pedido.getParcelas().get(i).setPedido(pedido);}


        return this.Repository.save(cadastrar);
    }
    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Pedido atualizar) {
        final Pedido marcaBanco = this.Repository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.Repository.save(atualizar);
    }


/*
    // adicionar limite
    @Transactional(rollbackFor = Exception.class)
    public Pedido pedidoMensalSimples(Pedido pedido) {


        Double valorInicial = pedido.getValorDoc();
        Double jurosInt = pedido.getJuros();
        Integer quantidade = pedido.getQuantidade();
        Double jurosDecimal = jurosInt / 100.0;
        Double valorJuros = valorInicial * jurosDecimal;
        Double parcela = valorJuros+(valorInicial / quantidade) ;
        Double total = parcela * quantidade;
        pedido.setValorLiquido(parcela);
        pedido.setTotal(total);
        return Repository.save(pedido);
    }
    @Transactional(rollbackFor = Exception.class)
    public Pedido pedidoMensalComposto(Pedido pedido) {

        Double valorInicial = pedido.getValorDoc();
        Double jurosInt = pedido.getJuros();
        Integer quantidade = pedido.getQuantidade();
        Double jurosDecimal = jurosInt / 100.0;
        Double resultado = valorInicial;
        for (int i = 0; i < quantidade; i++) {
            Double valorJuros = resultado * jurosDecimal;
            resultado+=valorJuros;
            System.out.print(resultado+"\n");
        }
        for(int i=0;i<pedido.getParcelas().size();i++){
            pedido.getParcelas().get(i).setPedido(pedido);
        }
        Double parcela = resultado/quantidade ;
        pedido.setValorLiquido(parcela);
        pedido.setTotal(resultado);
        return Repository.save(pedido);


    }

    @Transactional(rollbackFor = Exception.class)
    public Pedido pedidodiarioSimples(Pedido pedido) {

        Double valorInicial = pedido.getValorDoc();
        Double jurosInt = pedido.getJuros();
        Integer quantidade = pedido.getQuantidade();
        Double jurosDecimal = jurosInt / 100.0;
        final LocalDateTime saida = pedido.getVencimento();
        Duration duracao = Duration.between(pedido.getCriacao(), saida);
        long diferencaDias = duracao.toDays(); // Corrigido o nome da variável
        Double jurosDiario = jurosDecimal / 30;
        Double jurosTotal = jurosDiario * diferencaDias;
        Double parcela = valorInicial * jurosTotal;
        Double total = valorInicial - parcela;
        for(int i=0;i<pedido.getParcelas().size();i++){
            pedido.getParcelas().get(i).setPedido(pedido);
        }
        pedido.setValorLiquido(parcela);
        pedido.setTotal(total);
        pedido.setQuantidade((int) diferencaDias);
        return Repository.save(pedido);

    }

 */



    @Transactional(rollbackFor = Exception.class)
    public Pedido calcularTabelaPrice(Pedido pedido) {
        Double PV = pedido.getValorDoc();
        Double R = pedido.getJuros() / 100;
        Integer N = pedido.getQuantidade();
        Double P = (double) 0;
        Double saldo = PV;



        P = (PV * R * Math.pow((1 + R), N)) / (Math.pow((1 + R), N) - 1);



        pedido.setTotal(P);
        System.out.println("prestacao= " + P);
        System.out.println("Parcela | Juros | Amortizacao | Prestacao | Saldo");
        for (int i = 0; i < N; i++) {
            Double juros = saldo * R;
            Double amortizacao = P - juros;
            saldo -= amortizacao;
            if (Math.abs(saldo) < 1e-10) {
                saldo = 0.0;
            }
            System.out.println((i + 1) + " | " + juros + " | " + amortizacao + " | " + P + " | " + saldo);
        }
        return Repository.save(pedido);
    }





}
