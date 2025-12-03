package org.ufba.model;

import org.ufba.model.effects.Effect;

public class Card {

    private final String name;
    private final int damage;
    private final Effect effect;
    private final int effectValue; // Valor associado ao efeito (ex: quantidade de cura, multiplicador)

    public Card(String name, int damage, Effect effect, int effectValue) {
        this.name = name;
        this.damage = damage;
        this.effect = effect;
        this.effectValue = effectValue;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public Effect getEffect() {
        return effect;
    }

    public int getEffectValue() {
        return effectValue;
    }

    @Override
    public String toString() {
        return name + " [Dano: " + damage + ", Efeito: " + effect.name() + ", Valor: " + effectValue + "]";
    }
}
