package org.entdes.todolist;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TascaTest {

    private Tasca tasca;

    @BeforeEach
    void setUp() {
        tasca = new Tasca("Fer el test");
    }

    @Test
    void testConstructor() {
        assertEquals("Fer el test", tasca.getDescripcio());
        assertFalse(tasca.isCompletada());
        assertNotNull(tasca.getId());
    }

    @Test
    void testSetCompletada() {
        tasca.setCompletada(true);
        assertTrue(tasca.isCompletada());
    }

    @Test
    void testSetDescripcio() {
        tasca.setDescripcio("Nou descripcio");
        assertEquals("Nou descripcio", tasca.getDescripcio());
    }

    @Test
    void testSetDataInici() {
        LocalDate dataInici = LocalDate.of(2025, 2, 22);
        tasca.setDataInici(dataInici);
        assertEquals(dataInici, tasca.getDataInici());
    }

    @Test
    void testSetDataFiPrevista() {
        LocalDate dataFiPrevista = LocalDate.of(2025, 3, 1);
        tasca.setDataFiPrevista(dataFiPrevista);
        assertEquals(dataFiPrevista, tasca.getDataFiPrevista());
    }

    @Test
    void testSetDataFiReal() {
        LocalDate dataFiReal = LocalDate.of(2025, 3, 2);
        tasca.setDataFiReal(dataFiReal);
        assertEquals(dataFiReal, tasca.getDataFiReal());
    }

    @Test
    void testSetPrioritat() {
        tasca.setPrioritat(5);
        assertEquals(5, tasca.getPrioritat());
    }

    @Test
    void testToString() {
        assertEquals("Fer el test: Pendent", tasca.toString());
        tasca.setCompletada(true);
        assertEquals("Fer el test: Completada", tasca.toString());
    }
}
