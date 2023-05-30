package Fozesc.com.demo.Repository;

import Fozesc.com.demo.Entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo,Long> {
    List<Emprestimo> findByAtivo(boolean ativo);

}
