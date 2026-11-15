package battle_system;

import character.Character_Class;
import enemy.Enemy;
import enemy.Enemy_Quotes;
import java.util.Random;

public class Battle_System {
	private final Enemy_Quotes enemyQuotes = new Enemy_Quotes();
	private final Magic magic_attacks = new Magic();
	private final Formulas formulas = new Formulas();

	// Gold charged when a character is revived after being knocked out.
	private static final int REVIVE_COST = 100;

	/**
	 * @param player
	 * @param enemy
	 * @return
	 * @throws Exception
	 */
	private boolean simulateBattle(Character_Class player, Enemy enemy) throws Exception {
		Random random = new Random();
		int playerHealth = player.getHealth();
		// Magic acts as a per-battle resource pool, refilled to the character's
		// maximum once the battle ends.
		final int maxMagic = player.getMagic();
		int playerMagic = maxMagic;
		int enemyHealth = enemy.getHealth();
		int round = 1;

		System.out.println("A simulated battle begins: " + player.getName() + " vs. " + enemy.getName());
		while(playerHealth > 0 && enemyHealth > 0) {
			System.out.println("\nRound " + round++);
			if(playerHealth <= 30 && playerHealth < player.getMaxHealth() && player.backpack.hasItem("Health Potion")) {
				int healing = player.backpack.useItem("Health Potion");
				playerHealth = Math.min(player.getMaxHealth(), playerHealth + healing);
				System.out.println(player.getName() + " uses a Health Potion and restores " + healing + " HP.");
			}
			// Automatically restore magic when it is too low to cast and a Mana Potion is on hand.
			if(playerMagic < magic_attacks.get_Lightning_Bolt() && player.backpack.hasItem("Mana Potion")) {
				int restored = player.backpack.useItem("Mana Potion");
				playerMagic += restored;
				System.out.println(player.getName() + " uses a Mana Potion and restores " + restored + " magic.");
			}

			// Only consider spells the character can actually afford with remaining magic.
			boolean canFlameball = playerMagic >= magic_attacks.get_Flameball();
			boolean canLightning = playerMagic >= magic_attacks.get_Lightning_Bolt();

			String attackName;
			int playerDamage;
			int actionCap = (canFlameball || canLightning) ? 3 : 2;
			int playerAction = random.nextInt(actionCap);

			switch(playerAction) {
				case 0 -> {
					attackName = player.getPrimaryWeapon();
					playerDamage = formulas.getDamage(player.getPrimaryWeaponDamage(), enemy.getDefense());
				}
				case 1 -> {
					attackName = player.getSecondaryWeapon();
					if(attackName.equalsIgnoreCase("Punch")) {
						playerDamage = formulas.getPunchDamage(player.getStrength(), enemy.getArmor());
					}
					else {
						playerDamage = formulas.getDamage(player.getSecondaryWeaponDamage(), enemy.getDefense());
					}
				}
				default -> {
					// Pick randomly among the spells that are affordable.
					boolean flameball = canFlameball && canLightning
							? random.nextBoolean()
							: canFlameball;
					attackName = flameball ? magic_attacks.get_Flameball_Name() : magic_attacks.get_Lightning_Bolt_Name();
					int magicDamage = flameball ? magic_attacks.get_Flameball() : magic_attacks.get_Lightning_Bolt();
					playerMagic -= magicDamage;
					System.out.println("(" + attackName + " costs " + magicDamage + " magic, " + Math.max(0, playerMagic) + " left)");
					playerDamage = formulas.getDamage(magicDamage, enemy.getResistance());
				}
			}
			playerDamage = Math.max(1, playerDamage);
			enemyHealth -= playerDamage;
			System.out.println(player.getName() + " uses " + attackName + " and deals " + playerDamage + " damage.");

			if(enemyHealth > 0) {
				boolean magicAttack = enemy.getMagic() > 0 && (enemy.getDamage() == 0 || random.nextBoolean());
				String enemyAttack = magicAttack ? "magic" : "physical";
				int attackDamage = magicAttack ? enemy.getMagic() : enemy.getDamage();
				int defense = magicAttack ? player.getResistance() : player.getArmor();
				int enemyDamage = Math.max(1, formulas.getDamage(attackDamage, defense));
				playerHealth -= enemyDamage;
				System.out.println(enemy.getName() + " uses a " + enemyAttack + " attack and deals " + enemyDamage + " damage.");
			}

			System.out.println("You - " + player.getName() + " HP - " + Math.max(0, playerHealth)
					+ " | Magic - " + Math.max(0, playerMagic));
			System.out.println("Enemy - " + enemy.getName() + " HP - " + Math.max(0, enemyHealth));
		}

		// Magic is a per-battle pool: refill it so the next battle starts fresh.
		player.setMagic(maxMagic);

		if(playerHealth <= 0) {
			System.out.println("You are Dead");
			enemyQuotes.winningQuote();
			System.out.println("The Enemy Has Killed You.\n");
			revive(player);
			return false;
		}
		else {
			player.setHealth(playerHealth);
			System.out.println("The Enemy Has Been Defeated!\n");
			enemyQuotes.deathQuote();
			System.out.println("You Have " + playerHealth + " Health Points Remaining.\n");
			System.out.println("You have gained experience.\n");
			player.levelUp(20);
			return true;
		}
	}

	/**
	 * Restore a knocked-out character so the session can continue.
	 * Charges a gold penalty (capped at the gold the character actually has)
	 * and refills health to maximum.
	 */
	private void revive(Character_Class player) {
		int cost = Math.min(REVIVE_COST, Math.max(0, player.getMoney()));
		player.setMoney(player.getMoney() - cost);
		player.setHealth(player.getMaxHealth());
		System.out.println("A passing healer revives you for $" + cost + ".");
		System.out.println("You are revived with " + player.getHealth() + " HP and $" + player.getMoney() + " remaining.\n");
	}

	public boolean battleSystem(Character_Class player, Enemy enemy) throws Exception{
		return simulateBattle(player, enemy);
	}
}
