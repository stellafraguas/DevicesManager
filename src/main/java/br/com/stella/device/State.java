package br.com.stella.device;

import lombok.Getter;

@Getter
public enum State {
    AVAILABLE("Available"),
    IN_USE("In Use"),
    INACTIVE("Inactive");

    public final String label;

    State(String label) {
        this.label = label;
    }

    public static State valueOfLabel(String label) {
        for (State s : values()) {
            if (s.label.equalsIgnoreCase(label)) {
                return s;
            }
        }
        throw new IllegalArgumentException(String.format("Invalid State: %s. State must be one of: [%s, %s, %s]. ",
                label, AVAILABLE.getLabel(), IN_USE.getLabel(), INACTIVE.getLabel()));
    }
}
