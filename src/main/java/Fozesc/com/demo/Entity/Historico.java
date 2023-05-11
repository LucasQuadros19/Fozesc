package Fozesc.com.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Entity
@Audited
@AuditTable(value = "historicoaudited", schema = "audited")
@Table(name = "historico",schema = "public")
public class Historico extends AbstractEntity{
    @Getter
    @Setter
    @JoinColumn(name = "cliente",nullable = false)
    private Pessoa cliente;
    @Getter
    @Setter
    @Column(name = "cliedtPagante",nullable = false)
    private LocalDateTime dtPaga;
    @Getter
    @Setter
    @JoinColumn(name = "emprestimo",nullable = false)
     private Emprestimo emprestimo;

}
