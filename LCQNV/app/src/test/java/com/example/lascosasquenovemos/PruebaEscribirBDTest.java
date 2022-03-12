package com.example.lascosasquenovemos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PruebaEscribirBDTest{

    DBAccess pruebaEscribirBD;

    @Before
    public void setupTests() {
        pruebaEscribirBD = new DBAccess();
    }

    @Test
    public void pruebaEscribirDatosCorrectos() {
        String nombre = "Juan";
        String descripcion = "me gustan los macarrones";

        assertTrue(pruebaEscribirBD.comprobarFormato(nombre,descripcion));
    }

    @Test
    public void pruebaEscribirDatosIncorrectos1() {
        String nombre = "";
        String descripcion = "me gustan los macarrones";

        assertFalse(pruebaEscribirBD.comprobarFormato(nombre,descripcion));
    }

    @Test
    public void pruebaEscribirDatosIncorrectos2() {
        String nombre = "Juan";
        String descripcion = "";

        assertFalse(pruebaEscribirBD.comprobarFormato(nombre,descripcion));

    }

    @Test
    public void pruebaEscribirDatosIncorrectos3() {
        String nombre = "";
        String descripcion = "";

        assertFalse(pruebaEscribirBD.comprobarFormato(nombre,descripcion));
    }

}