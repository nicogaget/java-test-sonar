package fr.semifir;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatriceTest {

    @Test
    void addition() {
        int result = Calculatrice.addition(10,10,10);
        assertEquals(30, result);
    }
    @Test
    void soustraction() {
        int result = Calculatrice.soustraction(10,2,3);
        assertEquals(5, result);
    }
    @Test
    void multiplication() {
        int result = Calculatrice.multiplication(10,10);
        assertEquals(100, result);
    }
    @Test
    void division() {
        int result = Calculatrice.division(10,2);
        assertEquals(5, result);
    }
}
