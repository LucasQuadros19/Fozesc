package Fozesc.com.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@AuditTable(value = "emprestimoaudited", schema = "audited")
@Table(name = "emprestimo",schema = "public")
public class Emprestimo extends AbstractEntity {
    @Getter
    @Setter
    @Column(name = "valor",nullable = false)
    private double valor;

    @Getter
    @Setter
    @Column(name = "quantidade",nullable = false)
    private int quantidade;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "cliente",nullable = false)
    private AprovacaoStatus Aprovacao;
    @Getter
    @Setter
    @Column(name = "formaPaga",nullable = false)
    private Forma formaPaga;

}
