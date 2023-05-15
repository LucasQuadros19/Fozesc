package Fozesc.com.demo.Repository;

import Fozesc.com.demo.Entity.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao,Long> {
}
