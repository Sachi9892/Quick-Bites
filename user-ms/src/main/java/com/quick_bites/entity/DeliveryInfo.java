package com.quick_bites.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeliveryInfo {

    @Id
    private Long infoId;

    private DeliveryStatus deliveryStatus;

    private String deliveryInstruction;

    private String riderName;

    private String riderMobileNumber;

}
