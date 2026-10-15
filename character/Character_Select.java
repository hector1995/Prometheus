package character;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Character_Select {
	static int response = 0;
	static Scanner input = new Scanner(System.in);

	public static Character_Class character_Select(){
		while (true)
		{
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
				response = input.nextInt();
				if (response >=1 && response <=5)
				{
					break;
				}
				else
				{
					System.out.println("Please enter a number between 1-5");
				}
				}catch(InputMismatchException e)
				{
					System.out.println("Please enter a valid response");
					}input.nextLine();
			}
			switch (response)
			{
			case 1 -> {
                            Character_Warrior character = new Character_Warrior();
                            displayCharacter(character);
                            if(confirmCharacter())
                            {
                                return character;
                            }
                }
			case 2 -> {
                            Character_Orc orc_character = new Character_Orc();
                            displayCharacter(orc_character);
                            if(confirmCharacter()){
                                return orc_character;
                            }
                }
			case 3 -> {
                            Character_Wizard wizard_character = new Character_Wizard();
                            displayCharacter(wizard_character);
                            if(confirmCharacter())
                            {
                                return wizard_character;
                            }
                }
			case 4 -> {
                            Character_Elf elf_character = new Character_Elf();
                            displayCharacter(elf_character);
                            if(confirmCharacter())
                            {
                                return elf_character;
                            }
                }
			case 5 -> {
                            Character_Dwarf dwarf_character = new Character_Dwarf();
                            displayCharacter(dwarf_character);
                            if(confirmCharacter())
                            {
                                return dwarf_character;
                            }
                }
			default -> System.out.println("Invalid Response. Please enter a number from the list above.");
		}
			return null;
			}
	
		private static boolean confirmCharacter()
		{
			boolean character = false;
			System.out.println("Would you like to continue with the character?Y/N");
			String confirm_flag = input.next();
			
			if(confirm_flag.equalsIgnoreCase("Y"))
			{
				character= true;
			}
			else if(confirm_flag.equalsIgnoreCase("N"))
			{
				character_Select();
			}
			else
			{
				System.out.println("Please enter a valid input (Y/N):");
				confirm_flag = input.next();
				if(confirm_flag.equalsIgnoreCase("Y"))
				{
					character= true;
				}
				else if(confirm_flag.equalsIgnoreCase("N"))
				{
					character_Select();
				}
			}
			return character;
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

