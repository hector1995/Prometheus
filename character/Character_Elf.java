package character;

public class Character_Elf extends Character_Class {
	
	// NEW CONSTRUCTOR:
	
	public Character_Elf() {
			super.name = "Elf";
			super.health = 100;
			super.magic = 60;
			super.armor = 70;
			super.speed = 80;
			super.strength = 60;
			super.resistance = 40;
	}
		
	// END NEW CONSTRUCTOR.
	/** (non-Javadoc)
	 * @see character.Character_Class#levelUp(int)
	 */
	@Override
	public void levelUp(int exp1) {
		int levelsGained = addExperience(exp1);
		for(int levelIndex = 0; levelIndex < levelsGained; levelIndex++) {
				increaseMaxHealth(7);
				armor += 6;
				magic += 7;
				speed += 12;
				strength += 6;
				resistance += 9;
				System.out.println("You have leveled up!");
		}
	}
	
	@Override
	public String getSpeak(){
		return "I am an Elf";
	}
	
	public String getSpeak2(){
		return "I do: ";
	}
	
	public String getSpeak3(){
		return "I have: ";
	}
	
}
