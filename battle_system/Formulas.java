package battle_system;
import java.util.Random;

public class Formulas
{
	// BEGIN NEW DAMAGE FORMULAS.
	private final Random rand = new Random();
	
	public int getPunchDamage (int playerStrength, int enemyArmor)	// Method to be used for punches specifically.
	{
		int damage = 5 + (playerStrength/10) - (enemyArmor/10);
		int r = rand.nextInt(10);
		if (r >= 7)
			damage *= 2;
		return damage;
	}
	
	public int getDamage (int attackDamage, int enemyDefense)	// Method for calculating other damage (weapon/magic).
	{
		int damage = 10 + (attackDamage/10) - (enemyDefense/10);
		int r = rand.nextInt(10);
		if (r >= 7)
			damage = damage * 3 / 2;
		return damage;
	}
	
	// END NEW DAMAGE FORMULAS.
}
