# PocketBeasts

PocketBeasts is a console-based card game implemented in Java, designed to demonstrate core software design patterns in a game context. Players take turns playing beast cards, attacking, and managing resources to defeat their opponent.

## Features

- **Turn-based card game** for two players
- **Mana and health management** for each player
- **Deck, hand, in-play, and graveyard** mechanics
- **Attack and play phases** each turn
- **Multiple game modes** (standard, template-based, observer-based)
- **Game logging** and spectator (observer) support

## Software Design Patterns Used

- **Factory Pattern:** Used for creating card instances (see `FactoryPattern/BeastFactory.java`).
- **Decorator Pattern:** Allows dynamic enhancement of card abilities, e.g., boosting attack (`DecoratorPattern/AttackBoostEnhancement.java`).
- **Observer Pattern:** Implements spectators that observe and react to game events (`ObserverPatttern/Spectator.java`).
- **Template Pattern:** Used for structuring the game flow (`Template/GameRunner.java`).

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Build and Run

1. **Clone the repository** (if not already done):

   ```
   git clone <repo-url>
   cd PocketBeasts
   ```

2. **Build the project**:

   ```
   mvn clean package
   ```

3. **Run the game**:

   By default, the main class is set to run the Observer Pattern-based game mode (`TestRunner`). To run the standard game, comment out the TestRunner line in `Main.java` and uncomment the Game line. For example:

   ```java
   // TestRunner testGame = new TestRunner();
   // testGame.startGame();

   Game newGame = new Game();
   newGame.startGame();
   ```

## How to Play

- Each player starts with 15 health and 1 mana.
- Players draw 4 cards at the start.
- Each turn:
  1. Add mana (increases by 1 each turn, up to 9).
  2. Draw a card.
  3. Attack with cards in play (choose to attack the opponent or their beasts).
  4. Play cards from hand if you have enough mana.
- The game ends when a player's health drops to 0.

## Project Structure

- `src/main/java/cis2039/pocketbeasts/` - Core game logic
- `FactoryPattern/` - Factory pattern for card creation
- `DecoratorPattern/` - Decorators for card enhancements
- `ObserverPatttern/` - Observer pattern for spectators and game state
- `Template/` - Template pattern for game flow

