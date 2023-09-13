package Fozesc.com.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@AuditTable(value = "enderecoaudited", schema = "audited")
@Table(name = "Endereco",schema = "public")
@Getter
@Setter
public class Endereco extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Pessoa cliente;
    @Column(name = "cep",nullable = false,length = 30)
    private String cep;
    @Column(name = "cidade",nullable = false,length = 30)
    private String cidade;
    @Column(name = "bairro",nullable = false,length = 30)
    private String bairro;
    @Column(name = "rua",nullable = false,length = 30)
    private String rua;
    @Column(name = "numero",nullable = false,length = 30)
    private Integer numero;
}
