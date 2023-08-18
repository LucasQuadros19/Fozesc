package Fozesc.com.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Audited
@AuditTable(value = "pedidoAudited", schema = "audited")
@Table(name = "pedido",schema = "public")
public class Pedido extends AbstractEntity{
    @Getter
    @Setter
    @JoinColumn(name = "formaPaga",nullable = false)
    private Forma formaPaga;

    @Getter
    @Setter
    @Column(name = "valorDoc",nullable = false)
    private Double valorDoc;
    @Getter
    @Setter
    @Column(name = "juros",nullable = false)
    private Double juros;
    @Getter
    @Setter
    @Column(name = "valorLiquido")
    private Double valorLiquido;
    @Getter
    @Setter
    @Column(name = "quantidade",nullable = false)
    private int quantidade;
    @Getter
    @Setter
    @Column(name = "total")
    private Double total;
    @Getter
    @Setter
    @Column(name = "emitente",nullable = false,length = 30)
    private String emitente;
    @Getter
    @Setter
    @Column(name = "destino",nullable = false,length = 100)
    private Destino destino;
    @Getter
    @Setter
    @Column(name="criacao",nullable=false)
    private LocalDateTime criacao;
    @Getter
    @Setter
    @Column(name = "vencimento",nullable = false)
    private LocalDateTime vencimento;
    @Getter
    @Setter
    @Column(name = "situacao",nullable = false,length = 100)
    private Situacao situacao;
    @Getter
    @Setter
    @Column(name = "observacao",length = 100)
    private String observacao;
    @Getter
    @Setter
    @OneToMany(mappedBy = "pedido",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("pedido")
    private List<Historico> parcelas;
    @PrePersist
    private void prePersiste(){
        setCadastro(LocalDateTime.now());
        this.setCriacao(LocalDateTime.now());

    }
}
