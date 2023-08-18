package Fozesc.com.demo.Entity;

import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "pedido",nullable = false)
    private Pedido pedido;
    @Getter
    @Setter
    @Column(name = "proxPgamaneto",nullable = false)
    private LocalDateTime proxPgamaneto;


}
