package com.icai.practicas.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelefonoTest {

    @Test
    void validarWhenCorrect() {
        Telefono tel= new Telefono("695540956");
        assertEquals(true, tel.validar());
    }

    @Test
    void validarWhenNotCorrect() {
        Telefono tel= new Telefono("6955956");
        assertEquals(false, tel.validar());
    }
}