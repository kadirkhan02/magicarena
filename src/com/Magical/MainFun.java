package com.Magical;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class MainFun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number of Players joining the game: ");
        int N = sc.nextInt();
        sc.nextLine(); // Consume newline character
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            System.out.println("Enter details for Player " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Health: ");
            int health = sc.nextInt();
            System.out.print("Strength: ");
            int strength = sc.nextInt();
            System.out.print("Attack: ");
            int attack = sc.nextInt();
            sc.nextLine(); // Consume newline character

            players.add(new Player(name, health, strength, attack));
        }
        sc.close();

        while (players.size() > 1) {
            Player attacker = players.get(0);
            for (Player player : players) {
                if (player.getHealth() < attacker.getHealth() && player.isAlive()) {
                    attacker = player;
                }
            }

            Iterator<Player> iterator = players.iterator();
            while (iterator.hasNext()) {
                Player defender = iterator.next();
                if (defender != attacker && defender.isAlive()) {
                    attack(attacker, defender);
                    if (!defender.isAlive()) {
                        System.out.println(defender.getName() + " has been defeated!");
                        iterator.remove(); // Use iterator to safely remove
                    }
                }
            }
        }

        if (players.size() == 1) {
            System.out.println(players.get(0).getName() + " wins!");
        }
    }

    public static void attack(Player attacker, Player defender) {
        Random rand = new Random();
        int attackRoll = rand.nextInt(6) + 1;
        int defenseRoll = rand.nextInt(6) + 1;

        int attackDamage = attackRoll * attacker.getAttack();
        int defenseDamage = defenseRoll * defender.getStrength();

        int damageTaken = Math.max(0, attackDamage - defenseDamage);
        defender.setHealth(Math.max(0, defender.getHealth() - damageTaken));

        System.out.println(attacker.getName() + " attacks " + defender.getName() + " with roll " + attackRoll + " causing damage " + attackDamage);
        System.out.println(defender.getName() + " defends with roll " + defenseRoll + " blocking damage " + defenseDamage);
        System.out.println(defender.getName() + "'s health reduced to " + defender.getHealth() + "\n");
    }

    public static int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }
}
