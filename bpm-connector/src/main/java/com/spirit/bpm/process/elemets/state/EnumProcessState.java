package com.spirit.bpm.process.elemets.state;

import java.io.Serializable;

public enum EnumProcessState implements Serializable {

    HABILITADO, DESCONOCIDO;

    public static EnumProcessState getState(String s) {
        String estado = s.trim();
        if (estado.equalsIgnoreCase("ENABLED")) {
            return HABILITADO;
        } else {
            return DESCONOCIDO;
        }
    }
}
