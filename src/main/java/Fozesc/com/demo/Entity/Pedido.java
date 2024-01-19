package Fozesc.com.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Audited
@AuditTable(value = "pedidoAudited", schema = "audited")
@Table(name = "pedido",schema = "public")
public class Pedido extends AbstractEntity{

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "cliente",nullable = false)
    private Pessoa cliente;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "formaPaga",nullable = false)
    private FormaPagamento formaPaga;
    @Getter
    @Setter
    @Column(name = "valorDoc",nullable = false)
    private Double valorDoc;  // valor do pedido
    @Getter
    @Setter
    @Column(name = "juros",nullable = false)
    private Double juros; //ou taxa
    @Getter
    @Setter
    @Column(name = "amortizacao")
    private Double amortizacao;

    @Getter
    @Setter
    @Column(name = "quantidade",nullable = false)
    private int quantidade; // de parcelas
    @Getter
    @Setter
    @Column(name = "total")
    private Double total;

    @Getter
    @Setter
    @JsonIgnoreProperties("pedido")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<Cheques> cheques;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "situacao",nullable = false)
    private Situacao situacao;
    @Getter
    @Setter
    @Column(name = "observacao",length = 100)
    private String observacao;
    @Getter
    @Setter
    @OneToMany(mappedBy = "pedido",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("pedido")
    private List<Historico> parcelas;
    @Getter
    @Setter
    @Column(name = "aprovacao", nullable = false)
    private Boolean aprovacao;
    @PrePersist
    private void prePersiste(){
        setCadastro(LocalDateTime.now());
        this.aprovacao = true;
    }
}
