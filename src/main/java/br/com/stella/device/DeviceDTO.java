package br.com.stella.device;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class DeviceDTO {
    private Long id;

    @NotNull
    @Length(max = 100)
    private String name;

    @NotNull
    @Length(max = 100)
    private String brand;

    @NotNull
    @Length(max = 10)
    private String state;

    public Device toModel() {
        if (id == null)
            return new Device(name, brand, state);
        return new Device(id, name, brand, state);
    }
}
