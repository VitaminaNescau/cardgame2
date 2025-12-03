# Documentação de Padrões de Projeto no Card Game

Este documento explica os padrões de projeto de software (`Design Patterns`) utilizados na construção deste jogo de cartas e quando você deve considerar usá-los em seus próprios projetos.

---

## 1. Singleton

### O que é?
O padrão Singleton garante que uma classe tenha apenas uma única instância e fornece um ponto de acesso global a essa instância.

### Onde foi usado?
Foi implementado na classe `GameManager`.

```java
public class GameManager {
    private static GameManager instance;

    private GameManager() {
        // Construtor privado
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
}
```

### Por que foi usado aqui?
O jogo precisa de um único ponto de controle para gerenciar o estado geral, como os jogadores envolvidos, o progresso do turno e a condição de fim de jogo. Usar um Singleton para o `GameManager` garante que qualquer parte do código que precise verificar ou alterar o estado do jogo esteja interagindo com a mesma e única instância, evitando inconsistências.

### Quando usar o Singleton?
- **Recursos Compartilhados:** Quando você precisa gerenciar o acesso a um recurso compartilhado, como um arquivo de configuração, um pool de conexões de banco de dados ou um gerenciador de hardware.
- **Ponto de Acesso Global:** Quando você precisa de um objeto que seja facilmente acessível de várias partes do seu aplicativo sem ter que passá-lo como parâmetro para todos os métodos.
- **Controle de Instância Única:** Quando é crucial que exista apenas uma instância de uma classe. Por exemplo, uma classe que gerencia as configurações de um aplicativo.

**Cuidado:** O uso excessivo de Singletons pode levar a um código fortemente acoplado e dificultar os testes, pois o estado global é mais difícil de gerenciar em testes isolados.

---

## 2. Factory Method

### O que é?
O padrão Factory Method define uma interface para criar um objeto, mas deixa as subclasses decidirem qual classe instanciar. Essencialmente, ele encapsula a lógica de criação de objetos.

### Onde foi usado?
Foi implementado na classe `CardFactory` para criar diferentes tipos de cartas.

```java
public class CardFactory {
    public static Card createCard(String type) {
        switch (type) {
            case "Ataque Simples":
                return new Card("Ataque Simples", 10, "...");
            case "Ataque Forte":
                return new Card("Ataque Forte", 25, "...");
            default:
                throw new IllegalArgumentException(...);
        }
    }
}
```

### Por que foi usado aqui?
Centralizamos a criação de todas as cartas em um único local. Se quisermos adicionar uma nova carta ou alterar os atributos de uma carta existente, só precisamos modificar a `CardFactory`. O resto do código simplesmente solicita uma carta por seu tipo (ex: `CardFactory.createCard("Ataque Forte")`) sem precisar saber os detalhes de sua construção.

### Quando usar o Factory Method?
- **Criação de Objetos Complexa:** Quando a criação de um objeto envolve lógica complexa que você não quer duplicar em vários lugares.
- **Desacoplamento:** Quando você quer que seu código cliente não dependa das classes concretas dos objetos que ele precisa criar.
- **Flexibilidade:** Quando você quer dar às subclasses a responsabilidade de criar um tipo específico de objeto.

---

## 3. Abstract Factory

### O que é?
O padrão Abstract Factory fornece uma interface para criar famílias de objetos relacionados ou dependentes sem especificar suas classes concretas. Pense nele como uma "fábrica de fábricas".

### Onde foi usado?
Foi usado para criar "famílias" de personagens. Definimos a interface `CharacterFactory` e uma implementação concreta `StandardCharacterFactory`.

```java
// A interface (Fábrica Abstrata)
public interface CharacterFactory {
    Player createPlayer();
    Enemy createEnemy();
}

// A implementação (Fábrica Concreta)
public class StandardCharacterFactory implements CharacterFactory {
    @Override
    public Player createPlayer() {
        // Lógica para criar um jogador padrão
    }
    @Override
    public Enemy createEnemy() {
        // Lógica para criar um inimigo padrão
    }
}
```

### Por que foi usado aqui?
Este padrão estabelece uma base para futuras expansões. Atualmente, temos apenas uma "família" de personagens padrão. No futuro, poderíamos facilmente adicionar uma `FireCharacterFactory` que cria um `FirePlayer` e um `FireEnemy`, ambos com cartas e habilidades de fogo. O código principal do jogo não precisaria mudar; ele apenas seria inicializado com uma fábrica diferente.

### Quando usar o Abstract Factory?
- **Famílias de Objetos:** Quando seu sistema precisa criar famílias de produtos relacionados (ex: `Button`, `Checkbox` e `Window` para os temas `Windows` e `macOS`).
- **Independência de Implementação:** Quando você quer que seu sistema seja independente de como seus produtos são criados, compostos e representados.
- **Configuração de Temas/Skins:** É ideal para sistemas que precisam ser configurados com uma de várias famílias de produtos, como em UIs com temas diferentes.
