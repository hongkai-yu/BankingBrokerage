package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentityCheckerTest {

    private IdentityChecker identityChecker;

    @BeforeEach
    void setup() {
        identityChecker = new IdentityChecker("password");
    }

    @Test
    void testGetSetPassword() {
        assertEquals("password", identityChecker.getPassword());
        identityChecker.setPassword("Hello");
        assertEquals("Hello", identityChecker.getPassword());
    }
    @Test
    void testCheckPassword() {
        assertTrue(identityChecker.checkPassword("password"));
        assertFalse(identityChecker.checkPassword("Hello"));
    }
}