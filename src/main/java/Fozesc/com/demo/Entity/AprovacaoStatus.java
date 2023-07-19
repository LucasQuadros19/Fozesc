package Fozesc.com.demo.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@AuditTable(value = "aprovacaoAudited", schema = "audited")
@Table(name = "aprovacao", schema = "public")
public class AprovacaoStatus extends AbstractEntity {
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "limite",nullable = false)
    private Limite limite;
    @Getter
    @Setter
    @Column(name = "indicacao", nullable = false, length = 30)
    private String indicacao;
    @Getter
    @Setter
    @Column(name = "aprovacao", nullable = false)
    private boolean aprovacao;
    @Getter
    @Setter
    @Column(name = "status", nullable = false,length = 30)
    private Status status;
}

