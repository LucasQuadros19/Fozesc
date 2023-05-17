package Fozesc.com.demo.Service;

import Fozesc.com.demo.Entity.Pessoa;
import Fozesc.com.demo.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;

    public List<Pessoa> listagem(){
        return this.repository.findAll();
    }
}
