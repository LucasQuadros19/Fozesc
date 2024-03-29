package Fozesc.com.demo.Repository;

import Fozesc.com.demo.Entity.FormaPagamento;
import Fozesc.com.demo.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento,Long> {
    List<FormaPagamento> findByAtivo(boolean ativo);
}
