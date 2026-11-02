package character;

public class Character_Wizard extends Character_Class {
	
	// NEW CONSTRUCTOR:
	
		public Character_Wizard() {
			super.name = "Wizard";
			super.health = 100;
			super.magic = 100;
			super.armor = 20;
			super.speed = 50;
			super.strength = 40;
			super.resistance = 60;
		}
		
		// END NEW CONSTRUCTOR.
		@Override
		public void levelUp(int exp1) {
			int levelsGained = addExperience(exp1);
			for(int levelIndex = 0; levelIndex < levelsGained; levelIndex++) {
					health += 7;
					armor += 4;
					magic += 12;
					speed += 7;
					strength += 4;
					resistance += 10;
					System.out.println("You have leveled up!");
			}
		}
		
	@Override
	public String getSpeak(){
		return "I am a Wizard";
	}
	
	public String getSpeak2(){
		return "I do: ";
	}
	
	public String getSpeak3(){
		return "I have: ";
	}
	
}
