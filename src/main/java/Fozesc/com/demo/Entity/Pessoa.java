package Fozesc.com.demo.Entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
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
    @Column(name = "juros",nullable = false)
    private Double juros;
    @Getter
    @Setter
    @Column(name = "cpf",nullable = false,length = 19)
    private String cpf;
    @Getter
    @Setter
    @Column(name = "rg",nullable = false,length = 19)
    private String rg;
    @Getter
    @Setter
    @Column(name = "numero_Doc",nullable = false,length = 30)
    private String numeroDoc;
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "banco",nullable = false)
    private Banco banco;
    @Getter@Setter
    @OneToOne
    @JoinColumn(name= "situacao", nullable = false)
    private Situacao situacao;
    @Getter
    @Setter
    @Column(name= "limite",nullable = false)
    private Double limite;
    @Getter
    @Setter
    @Column(name = "cep",nullable = false,length = 30)
    private String cep;
    @Getter
    @Setter
    @Column(name = "cidade",nullable = false,length = 30)
    private String cidade;
    @Getter
    @Setter
    @Column(name = "bairro",nullable = false,length = 30)
    private String bairro;
    @Getter
    @Setter
    @Column(name = "rua",nullable = false,length = 30)
    private String rua;
    @Getter
    @Setter
    @Column(name = "numero",nullable = false,length = 30)
    private Integer numero;

}
