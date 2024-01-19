package Fozesc.com.demo.Service;

import Fozesc.com.demo.Entity.Banco;
import Fozesc.com.demo.Entity.Cheques;
import Fozesc.com.demo.Repository.BancoRepository;

import Fozesc.com.demo.Repository.ChequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
@Service
public class ChequeService {
    @Autowired
    private ChequeRepository Repository;
    public List<Cheques> listartudo(){
        return Repository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Cheques cadastrar(Cheques cadastrar) {
        return this.Repository.save(cadastrar);
    }
    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Cheques atualizar) {
        final Cheques marcaBanco = this.Repository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.Repository.save(atualizar);
    }
}
