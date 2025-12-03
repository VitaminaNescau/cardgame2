package org.ufba.model;

import org.ufba.model.effects.Effect;

import java.util.ArrayList;
import java.util.List;

public class Player {
    protected static final int MAX_HAND_SIZE = 6;

    protected String name;
    protected int health;
    protected int maxHealth;
    protected double critChance;

    protected Deck deck;
    protected List<Card> hand;
    protected int waveMultiplier = 1;

    public Player(String name, int maxHealth, double critChance, Deck deck) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.critChance = critChance;
        this.deck = deck;
        this.hand = new ArrayList<>();
        this.deck.shuffle();
    }

    public void drawCards(int amount) {
        for (int i = 0; i < amount; i++) {
            if (hand.size() >= MAX_HAND_SIZE) {
                System.out.println(name + " já tem a mão cheia (6 cartas)!");
                break;
            }
            if (!deck.isEmpty()) {
                hand.add(deck.draw());
            } else {
                System.out.println(name + " não tem mais cartas para comprar!");
                break;
            }
        }
    }

    public void playCard(Card card, Player target) {
        if (!hand.contains(card)) {
            System.out.println(name + " não tem a carta " + card.getName() + " na mão.");
            return;
        }

        System.out.println(name + " joga a carta: " + card.getName());
        hand.remove(card);

        // Orquestra a jogada
        applyCardEffect(card, target);
        int totalDamage = calculateAttackDamage(card);

        if (totalDamage > 0) {
            target.takeDamage(totalDamage);
        }
    }

    protected int calculateAttackDamage(Card card) {
        int totalDamage = card.getDamage();

        if (card.getEffect() == Effect.WAVE) {
            totalDamage *= waveMultiplier;
            System.out.println("Onda de Poder! Dano multiplicado para " + totalDamage);
            waveMultiplier += card.getEffectValue();
        } else {
            waveMultiplier = 1; // Reset se não for WAVE
        }
        
        // Lógica de dano com chance de crítico
        if (totalDamage > 0 && Math.random() < this.critChance) {
            totalDamage *= 2;
            System.out.println("Dano CRÍTICO!");
        }

        return totalDamage;
    }

    protected void applyCardEffect(Card card, Player target) {
        switch (card.getEffect()) {
            case ROUBO_DE_VIDA:
                System.out.println(name + " usa Roubo de Vida!");
                this.heal(card.getEffectValue());
                break;
            case CURA:
                System.out.println(name + " se cura!");
                this.heal(card.getEffectValue());
                break;
            case QUEBRA_ROBUSTEZ:
                if (target instanceof Enemy) {
                    ((Enemy) target).breakRobustez();
                }
                break;
            default:
                // DANO_SIMPLES, DANO_EXTREMO, WAVE (dano já calculado), NENHUM
                // não têm efeito extra aqui
                break;
        }
    }

    public void takeDamage(int amount) {
        this.health -= amount;
        System.out.println(name + " tomou " + amount + " de dano. Vida restante: " + getStatus());
        if (this.health <= 0) {
            this.health = 0;
            System.out.println(name + " foi derrotado!");
        }
    }

    public void heal(int amount) {
        this.health += amount;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
        System.out.println(name + " curou " + amount + " de vida. Vida atual: " + getStatus());
    }

    public String getStatus() {
        return health + "/" + maxHealth;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public List<Card> getHand() {
        return new ArrayList<>(hand);
    }

    public boolean isDefeated() {
        return this.health <= 0;
    }
}
