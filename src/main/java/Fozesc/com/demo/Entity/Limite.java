package Fozesc.com.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@AuditTable(value = "limiteAudited",schema = "audited")
@Table(name = "limite",schema = "public")
public class Limite extends AbstractEntity {
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente",nullable = false)
    private Pessoa cliente;
    @Getter
    @Setter
    @Column(name = "taxa",nullable = false)
    private Double taxa;
    @Getter
    @Setter
    @Column(name= "limite",nullable = false)
    private Double limite;
    @Getter
    @Setter
    @Column(name = "aprovacao", nullable = false)
    private Boolean aprovacao;
}