package entity;

import core.Main;
import util.TextUtil;

public class Enemy {
    private String name;
    private int level;
    private int maxHp;
    private int currentHp;
    private int defense;
    private int[] attacks;
    private int turnCount;
    private boolean canDefend;
    private String idleAscii;
    private String color;

    public Enemy(String name, int level, int maxHp, int defense, int[] attacks, String idleAscii, String color) {
        this.name = name;
        this.level = level;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.defense = defense;
        this.attacks = attacks;
        this.idleAscii = idleAscii;
        this.color = color;
        this.turnCount = 0;
        this.canDefend = level >= 2;
    }

    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defense);
        currentHp = Math.max(0, currentHp - actualDamage);
        TextUtil.typewriterPrint(name + " takes " + actualDamage + " damage! HP: " + currentHp + "/" + maxHp);
    }

    public int performAction() {
        turnCount++;
        if (level == 3 && turnCount % 3 == 0) {
            System.out.println(idleAscii);
            TextUtil.typewriterPrint(name + " unleashes a devastating special attack!");
            return 250;
        }
        if (canDefend && Main.random.nextInt(100) < 30) {
            TextUtil.typewriterPrint(name + " takes a defensive stance!");
            return 0;
        }
        System.out.println(idleAscii);
        TextUtil.typewriterPrint(name + " attacks with force!");
        return attacks[Main.random.nextInt(attacks.length)];
    }

    public void displayStats() {
        System.out.println("\n--- " + name + " Stats ---");
        System.out.println(idleAscii);
        System.out.println("Level: " + level);
        System.out.println("HP: " + currentHp + "/" + maxHp);
        System.out.println("DEF: " + defense);
    }

    public String getName() { return name; }
    public int getCurrentHp() { return currentHp; }
    public int getMaxHp() { return maxHp; }
    public int getLevel() { return level; }
    public int getDefense() { return defense; }
    public int[] getAttacks() { return attacks; }
    public boolean isAlive() { return currentHp > 0; }
    public String getIdleAscii() { return idleAscii; }
    public String getColor() { return color; }
}