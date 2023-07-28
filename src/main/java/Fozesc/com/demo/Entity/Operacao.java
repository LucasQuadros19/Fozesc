package Fozesc.com.demo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Entity
@Audited
@AuditTable(value = "operacaoAudited",schema = "audited")
@Table(name = "operacao",schema = "public")
public class Operacao extends AbstractEntity {

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "aprovacao",nullable = false)
    private AprovacaoStatus status;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "pedido",nullable = false)
    private Pedido pedido;
    @Getter
    @Setter
    @Column(name = "emitente",nullable = false,length = 30)
    private String emitente;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "emprestimo",nullable = false)
    private Emprestimo emprestimo;
    @Getter
    @Setter
    @Column(name = "dtPgto",nullable = false)
    private  LocalDateTime dtPgto;
    @Getter
    @Setter
    @Column(name = "destino",nullable = false,length = 100)
    private Destino destino;
    @Getter
    @Setter
    @Column(name = "vencimento",nullable = false)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDateTime vencimento;
    @Getter
    @Setter
    @Column(name = "situacao",nullable = false,length = 100)
    private Situacao situacao;
    @Getter
    @Setter
    @Column(name = "observacao",length = 100)
    private String observacao;
}
