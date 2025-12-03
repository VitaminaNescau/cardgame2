package org.ufba;

import org.ufba.model.Enemy;
import org.ufba.model.Player;

public class GameManager {

    private static GameManager instance;

    private Player player;
    private Enemy enemy;
    private boolean isGameOver;

    // O construtor privado impede a instanciação de fora da classe.
    private GameManager() {
        isGameOver = false;
    }

    /**
     * Padrão de Projeto: Singleton
     * 
     * Por que usar?
     * O Singleton é usado aqui para garantir que haja apenas uma instância do GameManager
     * durante todo o jogo. Isso é crucial para manter um estado de jogo global e 
     * consistente, como quem são os jogadores, de quem é o turno e se o jogo 
     * terminou. Evita a complexidade de passar uma instância do GameManager
     * por todas as classes que precisam interagir com o estado do jogo.
     * 
     * Como funciona:
     * - O construtor é privado, então ninguém pode fazer 'new GameManager()'.
     * - A variável 'instance' é estática e armazena a única instância da classe.
     * - O método estático 'getInstance()' é o único ponto de acesso. Ele cria a 
     *   instância na primeira vez que é chamado e a retorna em todas as chamadas
     *   subsequentes.
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void setupGame(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.isGameOver = false;
        System.out.println("O jogo começou! " + player.getName() + " vs " + enemy.getName());
        
        System.out.println("Comprando cartas iniciais...");
        player.drawCards(5);
        enemy.drawCards(5);
    }
    
    public void checkGameOver() {
        if (player.isDefeated()) {
            System.out.println("Fim de jogo! " + enemy.getName() + " venceu.");
            isGameOver = true;
        } else if (enemy.isDefeated()) {
            System.out.println("Fim de jogo! " + player.getName() + " venceu.");
            isGameOver = true;
        }
    }

    // Getters
    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
