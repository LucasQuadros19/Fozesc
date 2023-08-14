package Fozesc.com.demo.Service;

import Fozesc.com.demo.Entity.Emprestimo;
import Fozesc.com.demo.Entity.Limite;
import Fozesc.com.demo.Entity.Operacao;
import Fozesc.com.demo.Repository.EmprestimoRepository;
import Fozesc.com.demo.Repository.LimiteRepository;
import Fozesc.com.demo.Repository.OperacaoRepository;
import Fozesc.com.demo.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class OperacaoService {

    @Autowired
    private OperacaoRepository Repository;
    @Autowired
    private PedidoRepository  pRepository;
    @Autowired
    private EmprestimoRepository eRepository;
    public List<Operacao> listartudo(){

        return Repository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Operacao cadastrar(Operacao cadastrar) {
        Assert.notNull(cadastrar.getEmitente(),"Error, campo emitente vazio");
        return this.Repository.save(cadastrar);
    }
    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Operacao atualizar) {
        final Operacao marcaBanco = this.Repository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.Repository.save(atualizar);
    }
    @Transactional(rollbackFor = Exception.class)
    public void taxas(final Long id){

    }

}
