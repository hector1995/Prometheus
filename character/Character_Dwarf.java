package character;
/*This is the sub class for the Dwarf_Class.
 * This is where I add some return statements. */
public class Character_Dwarf extends Character_Class {
	
	// NEW CONSTRUCTOR:
	
	public Character_Dwarf() {
		super.name = "Dwarf";
		super.health = 100;
		super.magic = 30;
		super.armor = 50;
		super.speed = 50 ;
		super.strength = 60;
		super.resistance = 50;
	}
	
	// END NEW CONSTRUCTOR.
	
        @Override
	public void levelUp(int exp1) {
		int levelsGained = addExperience(exp1);
		for(int levelIndex = 0; levelIndex < levelsGained; levelIndex++) {
				increaseMaxHealth(11);
				armor += 9;
				magic += 5;
				speed += 4;
				strength += 11;
				resistance += 10;
				System.out.println("You have leveled up!");
		}
	}
	
        @Override
	public String getSpeak(){
		return "I am a Dwarf";
	}
	
	public String getSpeak2(){
		return "I do: ";
	}
	
	public String getSpeak3(){
		return "I have: ";
	}
	
}
