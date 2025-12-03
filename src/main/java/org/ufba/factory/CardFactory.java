package org.ufba.factory;

import org.ufba.model.Card;
import org.ufba.model.effects.Effect;
import org.ufba.model.enums.CardType;

public class CardFactory {

    public static Card createCard(CardType type) {
        switch (type) {
            case ATAQUE_SIMPLES:
                return new Card("Ataque Simples", 10, Effect.DANO_SIMPLES, 0);
            case ATAQUE_FORTE:
                return new Card("Ataque Forte", 25, Effect.DANO_SIMPLES, 0);
            case GOLPE_MORTAL:
                return new Card("Golpe Mortal", 50, Effect.DANO_EXTREMO, 0);
            case DRENAR_VIDA:
                return new Card("Drenar Vida", 15, Effect.ROUBO_DE_VIDA, 15);
            case ONDA_DE_PODER:
                return new Card("Onda de Poder", 5, Effect.WAVE, 2);
            case EXPOR_FRAQUEZA:
                return new Card("Expor Fraqueza", 5, Effect.QUEBRA_ROBUSTEZ, 0);
            case POCAO_DE_CURA:
                return new Card("Poção de Cura", 0, Effect.CURA, 20);
            default:
                // O compilador nos avisa se um enum não for tratado, mas por segurança:
                throw new IllegalArgumentException("Tipo de carta desconhecido: " + type);
        }
    }
}
