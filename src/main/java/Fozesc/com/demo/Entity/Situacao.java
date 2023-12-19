package Fozesc.com.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.security.PrivateKey;

@Entity
@Audited
@AuditTable(value = "situacaoudited", schema = "audited")
@Table(name = "situacao",schema = "public")
@Getter
@Setter
public class Situacao extends AbstractEntity {
    @Column(name = "situacao",nullable = false)
    private String situacao;
}
