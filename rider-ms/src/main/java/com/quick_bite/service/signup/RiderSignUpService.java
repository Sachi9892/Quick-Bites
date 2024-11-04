package com.quick_bite.service.signup;


import com.quick_bite.dto.RiderSignUpDto;
import com.quick_bite.entity.Rider;
import com.quick_bite.repository.CurrentAddressRepository;
import com.quick_bite.repository.RiderRepository;
import com.quick_bite.service.locationiq_services.GeoCodeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class RiderSignUpService {

    private final RiderRepository riderRepository;
    private final CurrentAddressRepository addressRepository;
    private final GeoCodeService geoCodeService;

    public Mono<Rider> signUpRider(RiderSignUpDto riderSignUpDTO) {


        return null;

    }

}
