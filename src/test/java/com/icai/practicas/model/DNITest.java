package com.icai.practicas.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DNITest {

    @Test
    void verdaderaWhenCorrect() {
        DNI dni= new DNI("54022272X");
        assertEquals(true,dni.validar());
    }
    @Test
    void falseWhenLetraMiddle() {
        DNI dni= new DNI("540D2272X");
        assertEquals(false,dni.validar());
    }
    @Test
    void falseWhenNumberEnd() {
        DNI dni= new DNI("540222721");
        assertEquals(false,dni.validar());
    }
    @Test
    void falseWhenInvalidDNI() {
        DNI dni= new DNI("99999999R");
        assertEquals(false,dni.validar());
    }
    @Test
    void falseWhenFormatNotCorrect() {
        DNI dni= new DNI("5402227X");
        assertEquals(false,dni.validar());
    }


}