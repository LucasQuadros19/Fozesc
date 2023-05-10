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
@AuditTable(value = "pessoaAudited", schema = "audited")
@Table(name = "pessoas",schema = "public")
public class Pessoa extends AbstractEntity {
    @Getter
    @Setter
    @Column(name = "nome",nullable = false,length = 100)
    private String nome;
    @Getter
    @Setter
    @Column(name = "cpf/cnpj",nullable = false,length = 19)
    private String cpfCnpj;
    @Getter
    @Setter
    @Column(name = "banco",nullable = false,length = 20)
    private Bancos banco;
    @Getter
    @Setter
    @Column(name = "nDoc",nullable = false,length = 10)
    private String nDoc;

}
