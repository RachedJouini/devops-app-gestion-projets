
package tn.esprit.backend;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleUnitTest {

    @Test
    void additionTest() {
        assertEquals(4, 2 + 2);
    }

    @Test
    void stringTest() {
        assertTrue("DevOps".toLowerCase().contains("dev"));
    }
}
