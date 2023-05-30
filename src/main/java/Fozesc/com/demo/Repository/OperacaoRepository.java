package Fozesc.com.demo.Repository;

import Fozesc.com.demo.Entity.AprovacaoStatus;
import Fozesc.com.demo.Entity.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao,Long> {
    List<Operacao> findByAtivo(boolean ativo);
}
