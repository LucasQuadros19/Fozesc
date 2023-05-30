package Fozesc.com.demo.Repository;

import Fozesc.com.demo.Entity.AprovacaoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AprovacaoStatusRepository extends JpaRepository<AprovacaoStatus,Long> {
    List<AprovacaoStatus> findByAtivo(boolean ativo);
}
