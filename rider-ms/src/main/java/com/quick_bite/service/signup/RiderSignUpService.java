package com.quick_bite.service.signup;


import com.quick_bite.client.RestaurantClient;
import com.quick_bite.dto.rider_dto.RiderSignUpDTO;
import com.quick_bite.entity.CurrentAddress;
import com.quick_bite.dto.LocationDto;
import com.quick_bite.entity.Rider;
import com.quick_bite.repository.CurrentAddressRepository;
import com.quick_bite.repository.RiderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RiderSignUpService {

    private final RiderRepository riderRepository;
    private final CurrentAddressRepository addressRepository;
    private final RestaurantClient restaurantClient;



    public Rider signUpRider(RiderSignUpDTO dto) {

        LocationDto locationDto = restaurantClient.getCoordinates(dto.getAddress());

        // Create and save the CurrentAddress
        CurrentAddress currentAddress = new CurrentAddress();

        currentAddress.setLatitude(locationDto.getLatitude());
        currentAddress.setLongitude(locationDto.getLongitude());

        addressRepository.save(currentAddress);

        // Create the Rider entity
        Rider rider = new Rider();
        rider.setName(dto.getName());
        rider.setMobileNumber(dto.getMobileNumber());
        rider.setEmail(dto.getEmail());
        rider.setCurrentAddress(currentAddress);


        // Save the Rider and return it
        return riderRepository.save(rider);

    }

}
