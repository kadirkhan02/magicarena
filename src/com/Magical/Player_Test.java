package com.Magical;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Player_Test {

    @Test
    public void testPlayerInitialization() {
        Player player = new Player("TestPlayer", 100, 10, 15);
        assertEquals("TestPlayer", player.getName());
        assertEquals(100, player.getHealth());
        assertEquals(10, player.getStrength());
        assertEquals(15, player.getAttack());
    }

    @Test
    public void testPlayerHealthReduction() {
        Player player = new Player("TestPlayer", 100, 10, 15);
        player.setHealth(50);
        assertEquals(50, player.getHealth());
    }

    @Test
    public void testPlayerAlive() {
        Player player = new Player("TestPlayer", 100, 10, 15);
        assertTrue(player.isAlive());
        player.setHealth(0);
        assertFalse(player.isAlive());
    }

    @Test
    public void testPlayerNegativeHealth() {
        Player player = new Player("TestPlayer", 100, 10, 15);
        player.setHealth(-10);
        assertEquals(-10, player.getHealth());
        assertFalse(player.isAlive());
    }
}

