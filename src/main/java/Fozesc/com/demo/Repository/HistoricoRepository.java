package Fozesc.com.demo.Repository;

import Fozesc.com.demo.Entity.Emprestimo;
import Fozesc.com.demo.Entity.Historico;
import Fozesc.com.demo.Entity.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico,Long> {
    List<Historico> findByAtivo(boolean ativo);

    List<Historico> findByOperacao(Operacao operacao);



}
