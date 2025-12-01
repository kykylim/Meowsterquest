package combat;

import core.Main;
import entity.Enemy;
import entity.player.CatBreed;
import entity.player.CatColor;
import entity.player.Player;
import util.ColorUtil;
import util.TextUtil;

public class BattleSystem {
    private Player player;
    private Enemy enemy;
    private int radiantBurstDamage = 0;

    public BattleSystem(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public boolean startBattle() {
        TextUtil.printCentered("\n--- BATTLE START! ---\n");
        player.displayStats();
        System.out.println();
        enemy.displayStats();
        Main.pause(2000);

        while (player.isAlive() && enemy.isAlive()) {
            playerTurn();

            if (!enemy.isAlive()) {
                break;
            }
            enemyTurn();

            if (!player.isAlive()) {
                break;
            }

            Main.pause(1500);
        }

        return player.isAlive();
    }

    private void playerTurn() {
        Main.clearScreen();
        TextUtil.printTitle("YOUR TURN");

        player.displayStats();
        System.out.println();
        enemy.displayStats();

        System.out.println("\n=== Choose Action ===");
        Attack[] attacks = player.getAttacks();

        for (int i = 0; i < attacks.length; i++) {
            Attack a = attacks[i];
            System.out.println((i + 1) + ". " + a.getName() +  " (DMG: " + a.getDamage() + ", Energy: " + a.getEnergyCost() + ")");
        }
        System.out.println((attacks.length + 1) + ". Defend (Halve next damage)");

        System.out.print("\nChoose action (1-" + (attacks.length + 1) + "): ");
        int choice = Main.getIntInput(1, attacks.length + 1);

        System.out.println();

        if (choice == attacks.length + 1) {
            player.defend();
        } else {
            // Show player ASCII art when attacking
            showPlayerAttackArt();

            int damage = player.attack(choice - 1);
            if (damage > 0) {
                if (radiantBurstDamage > 0) {
                    damage += radiantBurstDamage;
                    System.out.println(ColorUtil.orange(" Radiant Burst adds " + radiantBurstDamage + " damage!"));
                    radiantBurstDamage = 0;
                }
                enemy.takeDamage(damage);
            }
        }
        player.regenerateEnergy();
        player.incrementTurn();

        if (player.getColor() == CatColor.ORANGE && player.getTurnCount() % 3 == 0) {
            radiantBurstDamage = 300;
        }

        if (player.getColor() == CatColor.WHITE && player.getTurnCount() % 3 == 0) {
            player.heal(200);
        }

        if (player.getColor() == CatColor.BLACK && player.getTurnCount() % 3 == 0) {
            System.out.println("\n Extra turn granted by Shadow Speed! ");
            Main.pause(1500);
            if (enemy.isAlive()) {
                playerTurn();
            }
        }
    }

    private void showPlayerAttackArt() {
        CatBreed breed = player.getBreed();
        CatColor color = player.getColor();

        String art = breed.ColoredAsciiAttackArt(color);

        System.out.println(art);
        Main.pause(500);
    }

    private void enemyTurn() {
        Main.clearScreen();
        TextUtil.printTitle("ENEMY TURN");

        int damage = enemy.performAction();

        if (damage > 0) {
            player.takeDamage(damage);
        }

        Main.pause(500);
    }
}