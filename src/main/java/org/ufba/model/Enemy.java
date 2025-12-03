package org.ufba.model;

public class Enemy extends Player {

    private boolean fortalecimento;
    private boolean robustez;

    public Enemy(String name, int maxHealth, double critChance, Deck deck, boolean fortalecimento, boolean robustez) {
        super(name, maxHealth, critChance, deck);
        this.fortalecimento = fortalecimento;
        this.robustez = robustez;
    }

    @Override
    protected int calculateAttackDamage(Card card) {
        int baseDamage = super.calculateAttackDamage(card);

        if (fortalecimento && baseDamage > 0) {
            baseDamage *= 1.5; // Fortalecimento aumenta o dano em 50%
            System.out.println(name + " usou Fortalecimento e aumentou o dano para " + baseDamage);
        }

        return baseDamage;
    }

    @Override
    public void takeDamage(int amount) {
        int finalDamage = amount;
        if (robustez && finalDamage > 0) {
            finalDamage /= 2; // Robustez reduz o dano pela metade
            System.out.println(name + " usou Robustez e reduziu o dano para " + finalDamage);
        }
        super.takeDamage(finalDamage);
    }

    public void breakRobustez() {
        if (this.robustez) {
            System.out.println("A Robustez de " + name + " foi quebrada!");
            this.robustez = false;
        }
    }

    public boolean hasFortalecimento() {
        return fortalecimento;
    }

    public boolean hasRobustez() {
        return robustez;
    }
}
