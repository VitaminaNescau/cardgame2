package org.ufba;

import org.ufba.factory.CharacterFactory;
import org.ufba.factory.StandardCharacterFactory;
import org.ufba.model.Card;
import org.ufba.model.Enemy;
import org.ufba.model.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Usa a Abstract Factory para criar os personagens
        CharacterFactory factory = new StandardCharacterFactory();
        Player player = factory.createPlayer();
        Enemy enemy = factory.createEnemy();

        // Usa o Singleton para gerenciar o estado do jogo
        GameManager gameManager = GameManager.getInstance();
        gameManager.setupGame(player, enemy);

        Scanner scanner = new Scanner(System.in);

        // Game Loop
        while (!gameManager.isGameOver()) {
            // Player's Turn
            System.out.println("\n-------------------- SEU TURNO --------------------\n");
            System.out.println("Status: " + player.getName() + " [" + player.getStatus() + "] vs " + enemy.getName() + " [" + enemy.getStatus() + "]");
            System.out.println("Sua mão:");
            List<Card> hand = player.getHand();
            for (int i = 0; i < hand.size(); i++) {
                System.out.println((i + 1) + ": " + hand.get(i));
            }

            if (hand.isEmpty()) {
                System.out.println("Você não tem cartas na mão! Passando o turno.");
            } else {
                 System.out.print("Escolha uma carta para jogar (ou 0 para passar o turno): ");
                int choice = -1;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número.");
                    continue;
                }


                if (choice > 0 && choice <= hand.size()) {
                    Card selectedCard = hand.get(choice - 1);
                    player.playCard(selectedCard, enemy);
                } else if (choice == 0) {
                    System.out.println("Você passou o turno.");
                } else {
                    System.out.println("Escolha inválida.");
                }
            }
            
            System.out.println("\nFim do seu turno. Comprando uma carta...");
            player.drawCards(1); // Compra 1 carta no final do turno
            gameManager.checkGameOver();
            if (gameManager.isGameOver()) break;

            // Enemy's Turn
            System.out.println("\n-------------------- TURNO DO INIMIGO --------------------\n");
            List<Card> enemyHand = enemy.getHand();
            if (!enemyHand.isEmpty()) {
                Card enemyCard = enemyHand.get(new Random().nextInt(enemyHand.size()));
                enemy.playCard(enemyCard, player);
            } else {
                System.out.println(enemy.getName() + " não tem cartas para jogar!");
            }
            System.out.println("Fim do turno do inimigo. " + enemy.getName() + " compra uma carta.");
            enemy.drawCards(1);
            gameManager.checkGameOver();
        }

        scanner.close();
        System.out.println("\nFim de Jogo.");
    }
}
