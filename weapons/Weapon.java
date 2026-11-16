package weapons;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a purchasable/equippable item in the shop.
 * Weapons have damage > 0; consumables have damage 0.
 */
public class Weapon {
	private final String name;
	private final int price;
	private final int requiredLevel;
	private final int damage;

	// Central registry of weapon damage. Single source of truth.
	private static final Map<String, Integer> WEAPON_DAMAGE = new LinkedHashMap<>();
	static {
		WEAPON_DAMAGE.put("Rusty Axe", 10);
		WEAPON_DAMAGE.put("Knife", 15);
		WEAPON_DAMAGE.put("Dagger", 25);
		WEAPON_DAMAGE.put("Sword", 35);
		WEAPON_DAMAGE.put("Mace", 45);
	}

	public Weapon(String name, int price, int requiredLevel, int damage) {
		this.name = name;
		this.price = price;
		this.requiredLevel = requiredLevel;
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getRequiredLevel() {
		return requiredLevel;
	}

	public int getDamage() {
		return damage;
	}

	public boolean isWeapon() {
		return damage > 0;
	}

	/**
	 * @return the damage for a registered weapon name, or null if the name
	 *         is not a weapon.
	 */
	public static Integer damageFor(String name) {
		return WEAPON_DAMAGE.get(name);
	}
}
