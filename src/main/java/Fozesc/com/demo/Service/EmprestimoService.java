package Fozesc.com.demo.Service;

import Fozesc.com.demo.Entity.AprovacaoStatus;
import Fozesc.com.demo.Entity.Emprestimo;
import Fozesc.com.demo.Repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository Repository;
    public List<Emprestimo> listartudo(){
        return Repository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Emprestimo cadastrar(Emprestimo cadastrar) {
        Assert.notNull(cadastrar.getValor(), "Error, campo valor vazio");
        Assert.notNull(cadastrar.getQuantidade(), "Error, campo quantidade vazio");
        return this.Repository.save(cadastrar);
    }
    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Emprestimo atualizar) {
        final Emprestimo marcaBanco = this.Repository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.Repository.save(atualizar);
    }


}
