package Fozesc.com.demo.Repository;

import Fozesc.com.demo.Entity.AprovacaoStatus;
import Fozesc.com.demo.Entity.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao,Long> {
    List<Operacao> findByAtivo(boolean ativo);

 /*   @Query("SELECT o FROM Operacao o " +
            "JOIN o.status a " +
            "JOIN a.limite l " +
            "WHERE l.cliente.id = :clienteId")
    List<Operacao> findByClienteId(@Param("clienteId") Long clienteId);

  */








}
