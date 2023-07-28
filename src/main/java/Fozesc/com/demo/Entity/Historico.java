package Fozesc.com.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;


@Entity
@Audited
@AuditTable(value = "historicoaudited", schema = "audited")
@Table(name = "historico",schema = "public")
public class Historico extends AbstractEntity{
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "cliente",nullable = false)
    private Operacao operacao;


}
