package battle_system;

import character.Character_Class;
import enemy.Enemy;
import enemy.Enemy_Giant;
import enemy.Enemy_Goblin;
import enemy.Enemy_Grunt;
import enemy.Enemy_Halfling;
import enemy.Enemy_Necromancer;
import enemy.Enemy_Scorpion;
import enemy.Enemy_Shapeshifter;
import enemy.Enemy_Siren;
import enemy.Enemy_Troll;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Battle_Mode {
	private final Scanner input;
	private final Battle_System battle = new Battle_System();
	private final Random random = new Random();

	public Battle_Mode(Scanner input) {
		this.input = input;
	}

	public void hunt(Character_Class player) throws Exception {
		System.out.println("Welcome to Monster Hunt. Each victory grants experience to help you level up.");
		while(player.getHealth() > 0) {
			Enemy enemy = randomHuntEnemy(player.getLevel());
			System.out.println("A wild " + enemy.getName() + " appears!");
			if(!battle.battleSystem(player, enemy)) {
				System.out.println("Your hunt ends here.");
				return;
			}

			System.out.println("Fight another monster? (Y = continue, N = return to main menu)");
			if(!input.nextLine().trim().equalsIgnoreCase("Y")) {
				return;
			}
		}
	}

	private Enemy randomHuntEnemy(int level) {
		List<Enemy> enemies = new ArrayList<>(List.of(new Enemy_Grunt(), new Enemy_Goblin(), new Enemy_Halfling()));
		if(level >= 2) {
			enemies.add(new Enemy_Siren());
		}
		if(level >= 3) {
			enemies.add(new Enemy_Scorpion());
			enemies.add(new Enemy_Troll());
		}
		if(level >= 5) {
			enemies.add(new Enemy_Giant());
			enemies.add(new Enemy_Shapeshifter());
		}
		if(level >= 7) {
			enemies.add(new Enemy_Necromancer());
		}
		return enemies.get(random.nextInt(enemies.size()));
	}
}