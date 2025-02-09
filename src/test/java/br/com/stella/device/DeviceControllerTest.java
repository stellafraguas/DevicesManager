package br.com.stella.device;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeviceController.class)
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceRepository deviceRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        deviceRepository.deleteAll(); // Clean database before each test
    }

    @Test
    public void newDeviceCreatedTest() throws Exception {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setName("test_name");
        deviceDTO.setBrand("test_brand");
        deviceDTO.setState("Available");
        mockMvc.perform(put("/devices/persist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deviceDTO)))
                        .andExpect(status().isCreated());
    }

    @Test
    public void newDeviceWithInvalidState() {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setName("test_name");
        deviceDTO.setBrand("test_brand");
        deviceDTO.setState("test_state");
        try {
            mockMvc.perform(put("/devices/persist")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(deviceDTO)));
        } catch (Exception e){
            Assert.isInstanceOf(IllegalArgumentException.class, e.getCause());
        }
    }
}