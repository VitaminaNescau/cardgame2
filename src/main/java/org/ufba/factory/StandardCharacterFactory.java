package org.ufba.factory;

import org.ufba.model.Deck;
import org.ufba.model.Enemy;
import org.ufba.model.Player;
import java.util.ArrayList;
import java.util.List;
import org.ufba.model.Card;
import org.ufba.model.enums.CardType;

public class StandardCharacterFactory implements CharacterFactory {

    @Override
    public Player createPlayer() {
        List<Card> playerDeckCards = new ArrayList<>();
        // 8 Ataques Simples
        for(int i=0; i<8; i++) playerDeckCards.add(CardFactory.createCard(CardType.ATAQUE_SIMPLES));
        // 2 Ataques Fortes
        playerDeckCards.add(CardFactory.createCard(CardType.ATAQUE_FORTE));
        playerDeckCards.add(CardFactory.createCard(CardType.ATAQUE_FORTE));
        // 2 Poções de Cura
        playerDeckCards.add(CardFactory.createCard(CardType.POCAO_DE_CURA));
        playerDeckCards.add(CardFactory.createCard(CardType.POCAO_DE_CURA));
        // 2 Drenar Vida
        playerDeckCards.add(CardFactory.createCard(CardType.DRENAR_VIDA));
        playerDeckCards.add(CardFactory.createCard(CardType.DRENAR_VIDA));
        // 1 Onda de Poder
        playerDeckCards.add(CardFactory.createCard(CardType.ONDA_DE_PODER));
        // 1 Expor Fraqueza
        playerDeckCards.add(CardFactory.createCard(CardType.EXPOR_FRAQUEZA));
        
        Deck playerDeck = new Deck(playerDeckCards);
        return new Player("Herói Destemido", 100, 0.15, playerDeck);
    }

    @Override
    public Enemy createEnemy() {
        List<Card> enemyDeckCards = new ArrayList<>();
        // 10 Ataques Simples
        for(int i=0; i<10; i++) enemyDeckCards.add(CardFactory.createCard(CardType.ATAQUE_SIMPLES));
        // 3 Ataques Fortes
        for(int i=0; i<3; i++) enemyDeckCards.add(CardFactory.createCard(CardType.ATAQUE_FORTE));
        // 1 Golpe Mortal
        enemyDeckCards.add(CardFactory.createCard(CardType.GOLPE_MORTAL));

        Deck enemyDeck = new Deck(enemyDeckCards);
        return new Enemy("Troll de Guerra", 120, 0.1, enemyDeck, true, true); 
    }
}
