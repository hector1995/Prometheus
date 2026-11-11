package game;
import battle_system.Battle_Mode;
import character.Character_Class;
import character.Character_Select;
import java.util.Scanner;
import shopping.Shopping;
import story.Story_Mode;
import tournament.Tournament_Mode;

public class Main_Class
{
	public static void main(String [] args) throws Exception{
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to Prometheus!");
		
		/*
		 * There are 3 modes (Story Mode, Tournament and Shopping)
		 * Have the user select which mode he/she would like to enter
		 *
		 * */
		
		Story_Mode story = new Story_Mode(input);
		Battle_Mode battleMode = new Battle_Mode(input);
		Tournament_Mode tournament = new Tournament_Mode(input);
		Shopping shopping = new Shopping(input);
		
		/* The character has been chosen and will remain the same throughout the game.
		 * Reference to the character object may need to be passed to each mode in order to save progress from each mode.
		 */
		Character_Class player = Character_Select.character_Select(input);
		while(true){
			System.out.println("Please select a mode: Story Mode(1) | Shopping(2) | Wager(3) | Monster Hunt(4) | Exit(5)");
			String response = input.nextLine();
			try {
				switch (Integer.parseInt(response.trim())) {
					case 1 -> story.playStory(player);
					case 2 -> shopping.store(player);
					case 3 -> tournament.Gamble(player);
					case 4 -> battleMode.hunt(player);
					case 5 -> {
						System.out.println("Thanks for playing");
						return;
					}
					default -> System.out.println("Please enter a number between 1-5");
				}
			} catch(NumberFormatException e) {
				System.out.println("Please enter a valid response");
			}
			}
		}
	}
