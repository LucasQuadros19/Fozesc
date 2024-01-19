package Fozesc.com.demo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Audited
@AuditTable(value = "chequesaudited", schema = "audited")
@Table(name = "cheques",schema = "public")
@Getter
@Setter
public class Cheques extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Column(name = "numero",nullable = false)
    private String numero;
    @Column(name = "valor",nullable = false)
    private Double valor;
    @Column(name = "valorJuros",nullable = false)
    private Double valorJuros;
    @JsonFormat(pattern = "ddMMyyyy")
    @DateTimeFormat(pattern = "ddMMyyyy")
    @Column(name = "vencimento")
    private LocalDate vencimento;

}
