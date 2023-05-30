package Fozesc.com.demo.Repository;

import Fozesc.com.demo.Entity.Historico;
import Fozesc.com.demo.Entity.Limite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface LimiteRepository extends JpaRepository<Limite,Long> {
    List<Limite> findByAtivo(boolean ativo);
}
