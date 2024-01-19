package Fozesc.com.demo.Repository;

import Fozesc.com.demo.Entity.Banco;
import Fozesc.com.demo.Entity.Cheques;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChequeRepository extends JpaRepository<Cheques,Long> {
    List<Cheques> findByAtivo(boolean ativo);
}
