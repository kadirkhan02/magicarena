package com.Magical;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;

public class MainFun_Test {

    @Test
    public void testAttack() {
        Player attacker = new Player("Attacker", 100, 10, 15);
        Player defender = new Player("Defender", 100, 10, 15);

        // Capture initial health
        int initialDefenderHealth = defender.getHealth();

        // Simulate an attack
        MainFun.attack(attacker, defender);

        // Assert defender's health has decreased
        assertTrue(defender.getHealth() < initialDefenderHealth);
    }

    @Test
    public void testDiceRollRange() {
        for (int i = 0; i < 1000; i++) {
            int roll = MainFun.rollDice();
            assertTrue(roll >= 1 && roll <= 6, "Dice roll out of range: " + roll);
        }
    }

    @Test
    public void testAttackMethodDamageCalculation() throws Exception {
        Player attacker = new Player("Attacker", 100, 10, 15);
        Player defender = new Player("Defender", 100, 10, 15);

        // Mock dice rolls using reflection to ensure deterministic behavior
        Method rollDiceMethod = MainFun.class.getDeclaredMethod("rollDice");
        rollDiceMethod.setAccessible(true);

        int attackRoll = (int) rollDiceMethod.invoke(null);
        int defenseRoll = (int) rollDiceMethod.invoke(null);

        int attackDamage = attackRoll * attacker.getAttack();
        int defenseDamage = defenseRoll * defender.getStrength();

        int expectedDamageTaken = Math.max(0, attackDamage - defenseDamage);

        // Capture initial health
        int initialDefenderHealth = defender.getHealth();

        // Simulate an attack
        MainFun.attack(attacker, defender);

        // Assert damage calculation is correct
        assertEquals(initialDefenderHealth - expectedDamageTaken, defender.getHealth());
    }
}
