package Fozesc.com.demo.Repository;
import Fozesc.com.demo.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByAtivo(boolean ativo);

    @Query("SELECT COUNT(m) FROM Pessoa m WHERE m.nome = :nome")
    public int countByNome(@Param("nome") String nome);
}
