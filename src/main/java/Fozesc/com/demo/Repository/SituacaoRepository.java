package Fozesc.com.demo.Repository;

import Fozesc.com.demo.Entity.FormaPagamento;
import Fozesc.com.demo.Entity.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SituacaoRepository extends JpaRepository<Situacao,Long> {
    List<Situacao> findByAtivo(boolean ativo);
}
