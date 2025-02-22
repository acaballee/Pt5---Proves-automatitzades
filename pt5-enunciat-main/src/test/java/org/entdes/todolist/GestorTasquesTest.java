package org.entdes.todolist;

import java.time.LocalDate;

import org.entdes.mail.IEmailService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GestorTasquesTest {

    private GestorTasques gestorTasques;
    private IEmailService emailServiceMock;

    @BeforeEach
    void setUp() {
        emailServiceMock = Mockito.mock(IEmailService.class);
        gestorTasques = new GestorTasques(emailServiceMock, "test@example.com");
    }

   

   

    @Test
    void testEliminarTasca() throws Exception {
        int id = gestorTasques.afegirTasca("Descripció 3", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), 2);
        gestorTasques.eliminarTasca(id);
        assertEquals(0, gestorTasques.getNombreTasques());
    }

    @Test
    void testModificarTasca() throws Exception {
        int id = gestorTasques.afegirTasca("Descripció 4", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), 4);
        gestorTasques.modificarTasca(id, "Descripció modificada", false, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), 5);

        Tasca tasca = gestorTasques.obtenirTasca(id);
        assertEquals("Descripció modificada", tasca.getDescripcio());
        assertEquals(5, tasca.getPrioritat());
    }

    @Test
    void testObtenirTasca() throws Exception {
        int id = gestorTasques.afegirTasca("Descripció 5", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), 3);
        Tasca tasca = gestorTasques.obtenirTasca(id);
        assertNotNull(tasca);
        assertEquals("Descripció 5", tasca.getDescripcio());
    }

    @Test
    void testMarcarCompletada() throws Exception {
        int id = gestorTasques.afegirTasca("Descripció 6", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), 3);
        gestorTasques.marcarCompletada(id);
        Tasca tasca = gestorTasques.obtenirTasca(id);
        assertTrue(tasca.isCompletada());
    }

    @Test
    void testLlistarTasques() throws Exception {
        gestorTasques.afegirTasca("Descripció 7", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), 1);
        assertEquals(1, gestorTasques.llistarTasques().size());
    }

    @Test
    void testLlistarTasquesPerComplecio() throws Exception {
        int id = gestorTasques.afegirTasca("Descripció 8", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), 1);
        gestorTasques.marcarCompletada(id);
        assertEquals(1, gestorTasques.llistarTasquesPerComplecio(true).size());
    }

    @Test
    void testEnviamentCorreu() throws Exception {
        gestorTasques.afegirTasca("Descripció amb correu", LocalDate.now().plusDays(1), LocalDate.now().plusDays(2), 2);
        verify(emailServiceMock, times(1)).enviarCorreu(eq("test@example.com"), eq("Nova Tasca Creada"), anyString());
    }
}
