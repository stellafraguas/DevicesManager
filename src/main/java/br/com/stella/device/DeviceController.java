package br.com.stella.device;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
        Device device;
        if (newDevice.getId() != null && deviceRepository.existsById(newDevice.getId())) {
            //will attempt to update device as id was provided and record exists with id
            logger.info("Device with id {} exists and will be updated", newDevice.getId());
            device = deviceRepository.findById(newDevice.getId()).orElse(new Device());
            if (!device.getState().equals(State.IN_USE)) {
                //devices name and/or brand can only be updated if status is not In Use
                device.setName(newDevice.getName());
                device.setBrand(newDevice.getBrand());
            } else {
                logger.info("Name and Brand wont be updated as state for device with id %s is In Use");
            }
            device.setState(newDevice.getState());
        }
        device = newDevice.toModel();
        deviceRepository.save(device);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}