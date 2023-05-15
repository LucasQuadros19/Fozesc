package Fozesc.com.demo.Repository;

import Fozesc.com.demo.Entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico,Long> {
}
