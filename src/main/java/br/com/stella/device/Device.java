package br.com.stella.device;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private State state;

    private String name;
    private String brand;

    private final LocalDateTime CREATION_TIME = LocalDateTime.now();    //creation time should not be changed

    public Device(String name, String brand, String state) {
        this.name = name;
        this.brand = brand;
        this.state = State.valueOfLabel(state);     //ensure sting is a valid state
    }

    public Device(Long id, String name, String brand, String state) {
        this(name, brand, state);
        this.id = id;
    }

    public void setState(String state){
        this.state = State.valueOfLabel(state);     //ensure sting is a valid state
    }
}
