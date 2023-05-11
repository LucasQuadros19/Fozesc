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
@AuditTable(value = "aprovacaoAudited",schema = "audited")
@Table(name = "aprovacao",schema = "public")
public class Aprovacao extends AbstractEntity{
   // private Url documentos; exemplo
    @Getter
    @Setter
    @JoinColumn(name = "limite",nullable = false)
    private Limite limite;
    @Getter
    @Setter
    @Column(name = "indicacao",nullable = false,length = 30)
    private String indicacao;
    @Getter
    @Setter
    @Column(name = "aprovacao",nullable = false)
    private Boolean aprovacao;
}
