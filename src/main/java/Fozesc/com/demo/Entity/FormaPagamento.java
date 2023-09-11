package Fozesc.com.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@AuditTable(value = "FormaPagamentoaudited", schema = "audited")
@Table(name = "formaPagamento",schema = "public")
@Getter
@Setter
public class FormaPagamento extends AbstractEntity{
    private String formaPagamento;
}
