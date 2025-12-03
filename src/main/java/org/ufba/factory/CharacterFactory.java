package org.ufba.factory;

import org.ufba.model.Enemy;
import org.ufba.model.Player;

/**
 * Padrão de Projeto: Abstract Factory
 *
 * Por que usar?
 * A Abstract Factory é usada para criar famílias de objetos relacionados sem
 * especificar suas classes concretas. Aqui, uma "família" é um conjunto de
 * personagens (um Player e um Enemy) que pertencem a um mesmo tema ou tipo.
 *
 * Como funciona:
 * - Esta interface define um contrato para criar um Player e um Enemy.
 * - Classes concretas (como StandardCharacterFactory) implementarão esta interface
 *   para criar um conjunto específico de personagens.
 * - Isso nos permite trocar facilmente o "tema" do jogo. Poderíamos ter uma
 *   'FireCharacterFactory' que cria personagens e inimigos com tema de fogo,
 *   ou uma 'IceCharacterFactory', e o código principal não precisaria mudar,
 *   apenas a fábrica que ele usa.
 */
public interface CharacterFactory {
    Player createPlayer();
    Enemy createEnemy();
}
