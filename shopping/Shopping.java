package shopping;
import character.Character_Class;
import java.util.Scanner;
import weapons.Weapon;

public class Shopping
{
	private final Weapon[] weaponsStock = {new Weapon("Sword", 350, 1, Weapon.damageFor("Sword")), new Weapon("Knife", 50, 2, Weapon.damageFor("Knife")), new Weapon("Dagger", 125, 3, Weapon.damageFor("Dagger")), new Weapon("Mace", 350, 5, Weapon.damageFor("Mace"))};
	private final Weapon[] itemsStock = {new Weapon("Health Potion", 150, 1, 0), new Weapon("Mana Potion", 250, 2, 0), new Weapon("Antidote", 100, 3, 0), new Weapon("Energy Restore", 225, 4, 0), new Weapon("Burn Heal", 250, 5, 0)};
	
	private final Scanner input;
	private String response = null;
	private boolean currentlyHere, browsing = false; //using it for while loop so I don't have to use recursion

	public Shopping(Scanner input) {
		this.input = input;
	}
	
	public void store(Character_Class person) throws Exception
	{
		if (currentlyHere == false){
			System.out.println("Welcome to my store. Take a look at my wares.");
			System.out.println("You are level " + person.getLevel() + " and you currently have $" + person.getMoney() + "\n");
			try
			{
				Thread.sleep(1000);
			}
			catch(InterruptedException ie)
			{
			}
		}
		
		stockShop();
		
		currentlyHere = true;
		while (currentlyHere == true)
		{
			System.out.println("Weapons (1) / Consumables (2) / View Backpack(3) / Equip Weapon(4) /Exit Store (5)");
			response = input.nextLine();
			
			switch (response)
			{
				case "1" -> browseWeapons(person);
				case "2" -> browseItems(person);
				case "3" -> person.backpack.viewInventory();
				case "4" -> equipFromBackpack(person);
				case "5" -> exitStore();
				default -> System.out.println("Invalid input.");
			}
		}
	}
	
	/*
	 * Equip a weapon from the backpack as the primary weapon.
	 */
	private void equipFromBackpack(Character_Class person)
	{
		person.backpack.viewInventory();
		System.out.println("Enter the number of the weapon to equip:");
		try {
			int choice = Integer.parseInt(input.nextLine().trim());
			String weapName = person.backpack.selectWeapon(choice);
			if(person.equipWeapon(weapName)) {
				System.out.println(person.getName() + " now wields " + weapName
						+ " (damage " + person.getPrimaryWeaponDamage() + ").");
			}
			else {
				System.out.println(weapName + " cannot be equipped.");
			}
		} catch(NumberFormatException e) {
			System.out.println("Please enter a valid number.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 *  Stock Shop
	 */
	public void stockShop()
	{
		setWeaponsStock();
		setItemsStock();
	}
	
	/*
	 * Browse
	 */
	//Displays weapons then switch statement to handle player choices
	public void browseWeapons(Character_Class person) throws Exception
	{
		System.out.println("Weapons:"); // (Number to Type) [Price] - [Weapons]
		browsing = true;
			
		while (browsing == true)
		{    //using placeholder strings for parameters
			displayStock(weaponsStock);
			response = input.nextLine();
			switch (response)
			{
				case "0" -> buyItem(person, weaponsStock[0]); //Sword
				case "1" -> buyItem(person, weaponsStock[1]); //Knife
				case "2" -> buyItem(person, weaponsStock[2]); //Dagger
				case "3" -> buyItem(person, weaponsStock[3]); //Mace
				case "9", "exit", "back" -> browsing = false;
                                default -> System.out.println("Invalid input.");
			}
		}
	}
	
	//Displays weapons then switch statement to handle player choices
	public void browseItems(Character_Class person) throws Exception{
		System.out.println("Items:"); // (Number to Buy) [Price] - [Item]
		browsing = true;
		
		while (browsing == true){   //using placeholder strings for parameters
			displayStock(itemsStock);
			response = input.nextLine();
			switch (response){
				case "0" -> buyItem(person, itemsStock[0]); //Health Potion
				case "1" -> buyItem(person, itemsStock[1]); //Mana Potion
				case "2" -> buyItem(person, itemsStock[2]); //Antidote
				case "3" -> buyItem(person, itemsStock[3]); //Energy Restore
				case "4" -> buyItem(person, itemsStock[4]); //Burn Heal
				case "9", "exit", "back" -> browsing = false;
                                default -> System.out.println("Invalid input.");
			}
		}
	}
	
	/*
	 * Display Stock
	 */
	
	private void displayStock(Weapon[] stock)
	{
		System.out.println();
		for(int index = 0; index < stock.length; index++){
			System.out.println("("+ index +") "+  stock[index].getPrice() + " - "+ stock[index].getName() + " - " + "You must be Level " + stock[index].getRequiredLevel() + " to purchase.");
		}
		System.out.println("(9) Back to Store Menu");
	}

	/*
	 * Buy Item
	 */
	public void buyItem(Character_Class person, Weapon item) throws Exception
	{
		if(person.getMoney() >= item.getPrice() && person.getLevel() >= item.getRequiredLevel())
		{
			person.setMoney(person.getMoney() - item.getPrice());
			person.backpack.addToInventory(item.getName(), 1);//add item to inventory
			System.out.println("Here's your " + item.getName());
			System.out.println("You have $" + person.getMoney() + " left. \n Would you like anything else? \n");
		}
		else if(person.getMoney() >= item.getPrice() && person.getLevel() < item.getRequiredLevel())
		{
			System.out.println("You don't meet the level requirements for this purchase");
		}
		else
		{
			System.out.println("Not enough gold. \nMaybe try farming a local dungeon. Probably some thieves still around.");
		}
	}
	
	/*
	 * Leave store (exit shopping)
	 */
	
	public void exitStore()
	{
		currentlyHere = false;
	}
	
	/*
	 * Getters and Setters
	 */
	
	public void setWeaponsStock()
	{
		//foreach normal weapon
		//	add weapon to weaponsStock arraylist
	}
	
	public void setItemsStock()
	{
		
	}
	}
