package tournament;
import character.Character_Class;
import java.util.Random;
import java.util.Scanner;

public class Tournament_Mode {
	private final Scanner input;

	public Tournament_Mode(Scanner input) {
		this.input = input;
	}

	public void Gamble(Character_Class player)throws Exception{
		System.out.println("Welcome to Wager Mode");
		int stage = 1;
		int choice;
		int withdraw;
		int prize;
		int cycle = 0;
		Random rndm = new Random();
		System.out.println("Welcome to coin toss!");
		Pause();
		System.out.println("Bet money and pick between head or tails.");
		Pause();
		System.out.println("If your right you can keep the money and more.");
		Pause();
		System.out.println("If your wrong you lose the money you bet");
		Pause();
		System.out.println("Good Luck");
		Pause();
		while(true){
		System.out.println("You have $" + player.getMoney());
		Pause();
		System.out.println("How much money do you want to bet?");
		withdraw = readBet(player);
		player.setMoney(player.getMoney() - withdraw);
		System.out.println("You now wagered: $" + withdraw);
		System.out.println("You now have $" + player.getMoney());
		Pause();
			int output = readHeadOrTails();

		choice = rndm.nextInt(100);
		
		System.out.println("Coin flip......");
		Pause();
		if(choice >= Outcome(stage)){
			System.out.println("You win!");
			int wPrize = Prize(stage);
			prize = withdraw + wPrize;
			System.out.println("You got an extra: $" + wPrize);
			System.out.println("You now have: $" + prize);
			player.setMoney(player.getMoney() + prize);
		}
		else{
			System.out.println("You lose");
			System.out.println("You have lost the wagered: $" + withdraw);
			System.out.println("You now have: $" + player.getMoney());
			}
		
		System.out.println("Do you want to continue? (y/n)");
		char c = readYesNo();
		if(c == 'n'){
			break;
		}
		if (c == 'y')
		{
			cycle++;
			int times = Levelup(stage, cycle);
			if (stage < 5)
			{
				System.out.println("You are currently in stage: " + stage + "\n");
			}
			if(times <= 0){
				stage = Math.min(stage + 1, 5);
				System.out.println("Entering stage: " + stage + "\n");
				cycle = 0;
				}
			if(stage < 5 && times > 0)
			{
				System.out.println("You can stay on this stage for " + times + " more turn(s) before you level up");
				System.out.println("Would you like to go up a stage? (y/n)");
				c = readYesNo();
				if (c == 'y')
				{
					stage = Math.min(stage + 1, 5);
					System.out.println("Entering stage: " + stage + "\n");
					cycle = 0;
				}
			}
		}
	}
		System.out.println("Now leaving Wager Mode\n");
	}

	/**
	 * Read a wager amount, rejecting non-numeric, zero, negative and
	 * over-balance entries.
	 */
	private int readBet(Character_Class player) {
		while(true) {
			try {
				int withdraw = Integer.parseInt(input.nextLine().trim());
				if(withdraw <= 0) {
					System.out.println("Bet must be a positive amount. Pick again.");
					continue;
				}
				if(player.getMoney() < withdraw) {
					System.out.println("Not enough money! Pick a different value.");
					continue;
				}
				return withdraw;
			} catch(NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		}
	}

	/**
	 * Read the head/tails choice (1 or 2) with validation.
	 */
	private int readHeadOrTails() {
		while(true) {
			System.out.println("Pick: head (1)| tails (2)");
			Pause();
			try {
				int output = Integer.parseInt(input.nextLine().trim());
				if(output == 1 || output == 2) {
					return output;
				}
				System.out.println("Please enter 1 or 2.");
			} catch(NumberFormatException e) {
				System.out.println("Please enter a valid number.");
			}
		}
	}

	/**
	 * Read a y/n answer, returning lowercase 'y' or 'n'.
	 */
	private char readYesNo() {
		while(true) {
			String token = input.nextLine().trim().toLowerCase();
			if(token.equals("y") || token.equals("n")) {
				return token.charAt(0);
			}
			System.out.println("Please enter y or n.");
		}
	}
	/**
	 * Method to enter player into tournament
	 * Currently has infinite loop on purpose until
	 * reward and tournament battle system is implemented
	 * @param player - User character
	 * @throws Exception
	 */
	
	
	public void Pause()
	{
		try{
			Thread.sleep(1500);
		}
		catch(InterruptedException ie)
		{
			Thread.currentThread().interrupt();
		}
	}
	public int Prize(int stage){
		Random rndm = new Random();
            return switch (stage)
			{
                case 1 -> rndm.nextInt(50) + 20;
                case 2 -> rndm.nextInt(50) + 70;
                case 3 -> rndm.nextInt(50) + 120;
                case 4 -> rndm.nextInt(50) + 170;
                default -> rndm.nextInt(50) + 220;
            };
	}
	public double Outcome(int stage)
	{
            return switch (stage)
			{
                case 1 -> 20;
                case 2 -> 35;
                case 3 -> 50;
                case 4 -> 65;
                default -> 80;
            };
	}
	public int Levelup(int stage, int cycle)
	{
            return switch (stage)
			{
                case 1 -> 2 - cycle;
                case 2 -> 3 - cycle;
                case 3 -> 4 - cycle;
                case 4 -> 5 - cycle;
                default -> 200;
            };
	}
}

