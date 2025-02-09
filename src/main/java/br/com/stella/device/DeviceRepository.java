package br.com.stella.device;

import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findByBrand(@Valid String brand);

    List<Device> findByState(State state);
}
