package com.quick_bites.entity;


import jakarta.persistence.Entity;
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

    private Long infoId;

    private DeliveryStatus deliveryStatus;

    private String deliveryInstruction;

    private String riderName;

    private String riderMobileNumber;
}
