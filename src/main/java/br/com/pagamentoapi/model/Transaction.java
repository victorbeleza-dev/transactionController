package br.com.pagamentoapi.model;

import br.com.pagamentoapi.utils.TransactionDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonDeserialize(using = TransactionDeserializer.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalDateTime time;

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private CardApplication cardApplication;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public Transaction(LocalDate date, LocalDateTime time, BigDecimal value, CardApplication cardApplication, PaymentStatus status) {
        this.date = date;
        this.time = time;
        this.value = value;
        this.cardApplication = cardApplication;
        this.status = status;
    }
}
