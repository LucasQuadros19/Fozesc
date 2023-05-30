package Fozesc.com.demo.Service;

import Fozesc.com.demo.Entity.AprovacaoStatus;
import Fozesc.com.demo.Repository.AprovacaoStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class AprovacaoStatusService {
    @Autowired
    private AprovacaoStatusRepository Repository;
    public List<AprovacaoStatus> listartudo(){

        return Repository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public AprovacaoStatus cadastrar(AprovacaoStatus cadastrar) {
        Assert.notNull(cadastrar.getIndicacao(), "Error, campo indicacao vazio");
        return this.Repository.save(cadastrar);
    }
    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, AprovacaoStatus atualizar) {
        final AprovacaoStatus marcaBanco = this.Repository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.Repository.save(atualizar);
    }


}
