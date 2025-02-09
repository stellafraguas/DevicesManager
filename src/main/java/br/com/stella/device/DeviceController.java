package br.com.stella.device;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceRepository deviceRepository;
    private final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Transactional
    @PutMapping("/persist")
    public ResponseEntity<?> persistDevice(@RequestBody @Valid DeviceDTO newDevice) throws IllegalArgumentException {
        Device device = newDevice.toModel();
        deviceRepository.save(device);
        logger.info("Device persisted: " + device.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}