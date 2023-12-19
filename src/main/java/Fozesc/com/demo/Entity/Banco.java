package Fozesc.com.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@AuditTable(value = "bancoaudited", schema = "audited")
@Table(name = "banco",schema = "public")
@Getter
@Setter

public class Banco extends AbstractEntity{
    @Column(name = "banco",nullable = false)
    private String banco;
}
