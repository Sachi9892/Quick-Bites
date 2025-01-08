package com.quick_bite.service.rider_manager.rider_assignment;

import com.quick_bite.dto.rider_dto.RiderDetails;
import com.quick_bite.dto.rider_dto.RiderLocationUpdate;
import com.quick_bite.service.utility.DistanceCalculator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Service
@AllArgsConstructor
@Slf4j
public class FindNearestRiderService {

    private final ConcurrentHashMap<String, RiderDetails> riderDetailsMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void addRiderSession(String riderId, WebSocketSession session) {
        sessions.put(riderId, session);
        riderDetailsMap.put(riderId, new RiderDetails(riderId, 0.0, 0.0, "FREE"));
    }

    public void updateRiderLocation(String riderId, RiderLocationUpdate locationUpdate) {

        RiderDetails rider = riderDetailsMap.get(riderId);

        if (rider != null) {
            rider.setLatitude(locationUpdate.getLatitude());
            rider.setLongitude(locationUpdate.getLongitude());
            rider.setStatus("FREE"); // Default to free
            log.info("Updated location for Rider {}: {}", riderId, rider);
        }
    }

    public void removeRiderSession(String riderId) {
        sessions.remove(riderId);
        riderDetailsMap.remove(riderId);
    }

    public RiderDetails findNearestFreeRider(double restaurantLat, double restaurantLon) {

        List<RiderDetails> freeRiders = riderDetailsMap.values().stream()
                .filter(rider -> "FREE".equalsIgnoreCase(rider.getStatus()))
                .toList();

        return freeRiders.stream()
                .min(Comparator.comparingDouble(r -> DistanceCalculator.calculateDistance(restaurantLat, restaurantLon, r.getLatitude(), r.getLongitude())))
                .orElse(null);
    }


    public WebSocketSession getSession(String riderId) {
        return sessions.get(riderId);
    }

}
