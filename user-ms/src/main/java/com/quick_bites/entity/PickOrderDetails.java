package com.quick_bites.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Pick Order Details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PickOrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long orderId;
    private Long restaurantId;

    private String restaurantName;
    private String pickUpAddress; //Basically a restaurant Address
    private Double restaurantLatitude;
    private Double restaurantLongitude;


    private Double userLatitude;
    private Double userLongitude;


/** I will these details from the rider - ms
 *
    private Double pickUpDistance;
    private Double dropDistance;
    private Double totalDistance;

    private Double expectedEarnings;
 */

}
