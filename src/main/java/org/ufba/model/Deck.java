package org.ufba.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    public static final int MAX_DECK_SIZE = 20;
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public Deck(List<Card> cards) {
        if (cards.size() > MAX_DECK_SIZE) {
            throw new IllegalArgumentException("O deck não pode ter mais de " + MAX_DECK_SIZE + " cartas.");
        }
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        if (this.cards.size() < MAX_DECK_SIZE) {
            this.cards.add(card);
        } else {
            System.out.println("O deck está cheio! Não é possível adicionar mais cartas.");
        }
    }

    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Card draw() {
        if (this.cards.isEmpty()) {
            return null; // Ou lançar uma exceção
        }
        return this.cards.remove(0);
    }

    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }

    public int size() {
        return this.cards.size();
    }
}