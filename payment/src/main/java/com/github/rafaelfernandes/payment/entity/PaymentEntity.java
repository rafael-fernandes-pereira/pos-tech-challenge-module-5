package com.github.rafaelfernandes.payment.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_payment")
public class PaymentEntity {

    @Id
    UUID id;

    UUID userid;

    UUID checkoutId;

    Double priceFinal;

}
