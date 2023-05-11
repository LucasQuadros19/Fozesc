package Fozesc.com.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@AuditTable(value = "pedidoAudited", schema = "udited")
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
    @Column(name = "valorLiquido",nullable = false)
    private Double valorLiquido;
    @Getter
    @Setter
    @Column(name = "total",nullable = false)
    private Double total;
}
