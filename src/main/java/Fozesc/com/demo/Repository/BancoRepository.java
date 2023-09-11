package Fozesc.com.demo.Repository;

import Fozesc.com.demo.Entity.Banco;
import Fozesc.com.demo.Entity.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BancoRepository extends JpaRepository<Banco,Long> {
    List<Banco> findByAtivo(boolean ativo);
}
