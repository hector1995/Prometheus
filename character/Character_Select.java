package character;

import java.util.Scanner;

public class Character_Select {

	public static Character_Class character_Select(Scanner input){
		while (true)
		{
			int response;
			try
			{
				System.out.println("""
					Which character would you like to play as for this session?:
					\t1.) Warrior
					\t2.) Orc
					\t3.) Wizard
					\t4.) Elf
					\t5.) Dwarf
								""");
				response = Integer.parseInt(input.nextLine().trim());
			}catch(NumberFormatException e)
			{
				System.out.println("Please enter a valid response");
				continue;
			}

			Character_Class character = switch (response)
			{
				case 1 -> new Character_Warrior();
				case 2 -> new Character_Orc();
				case 3 -> new Character_Wizard();
				case 4 -> new Character_Elf();
				case 5 -> new Character_Dwarf();
				default -> null;
			};

			if (character == null)
			{
				System.out.println("Please enter a number between 1-5");
				continue;
			}

			displayCharacter(character);

			if (confirmCharacter(input))
			{
				return character;
			}
			// User answered N: loop back and re-prompt for a new character.
		}
	}

	private static boolean confirmCharacter(Scanner input)
	{
		while (true)
		{
			System.out.println("Would you like to continue with the character? Y/N");
			String confirm_flag = input.nextLine().trim();

			if (confirm_flag.equalsIgnoreCase("Y"))
			{
				return true;
			}
			else if (confirm_flag.equalsIgnoreCase("N"))
			{
				return false;
			}
			System.out.println("Please enter a valid input (Y/N):\n");
		}
	}

		public static void displayCharacter (Character_Class player)
		{
			System.out.println("\nYou have chosen to be: " + player.getName());
			System.out.println("You start out at Level " + player.getLevel() +
							" and you have $" + player.getMoney() + " in your backpack.");
			
			System.out.println("\n-----------------------------");
			System.out.println("Character Traits");
			System.out.println("-----------------------------");
			
			System.out.printf("%-10s %-15s %-15s%n", "Speed", "Strength", "Health");
			System.out.printf("%-10s %-15s %-15s%n",
							player.getSpeed(),
							player.getStrength(),
							player.getHealth());
							
			System.out.println("");
			
			System.out.printf("%-10s %-15s %-15s%n", "Armor", "Resistance", "Magic");
			System.out.printf("%-10s %-15s %-15s%n",
							player.getArmor(),
							player.getResistance(),
							player.getMagic());
							
			System.out.println("\n-----------------------------");
			System.out.println("Weapons");
			System.out.println("-----------------------------");
			System.out.printf("Primary weapon: %s%n", player.getPrimaryWeapon());
			System.out.printf("Damage: %s%n", player.getPrimaryWeaponDamage());
			System.out.println("");
			System.out.printf("Secondary weapon: %s%n", player.getSecondaryWeapon());
			System.out.printf("Damage: %s%n", player.getSecondaryWeaponDamage());
			System.out.println();
		}
}

