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
@AuditTable(value = "destinoaudited", schema = "audited")
@Table(name = "destino",schema = "public")
@Getter
@Setter
public class Destino extends AbstractEntity{
    @Column(name = "destino",nullable = false)
    private String destino;
}
