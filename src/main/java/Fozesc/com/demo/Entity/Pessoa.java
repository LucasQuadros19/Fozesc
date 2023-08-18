package Fozesc.com.demo.Entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Audited
@AuditTable(value = "pessoaAudited", schema = "audited")
@Table(name = "pessoa",schema = "public")
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
    @Column(name = "numero_Doc",nullable = false,length = 30)
    private String numeroDoc;
    @Getter
    @Setter
    @Column(name = "banco",nullable = false,length = 20)
    private Bancos banco;
    @Getter@Setter
    @Column(name= "permissao", nullable = false)
    private boolean permissao;





}
