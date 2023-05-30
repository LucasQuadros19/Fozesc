package Fozesc.com.demo.Repository;

import Fozesc.com.demo.Entity.Operacao;
import Fozesc.com.demo.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    List<Pedido> findByAtivo(boolean ativo);
}
