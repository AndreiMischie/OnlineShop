package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.service.LocationService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public Collection<Location> getLocations() {
        return locationRepository.findAll();
    }
}
