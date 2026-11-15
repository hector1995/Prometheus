package shopping;
import java.util.LinkedHashMap;
import java.util.Map;

public class Inventory {
    private static final int STARTING_ITEM_COUNT = 4;
    private final Map<String, Integer> backpack = new LinkedHashMap<>();
    private int backpackSize;

    /**
     * Constructor for Inventory
     * with a default backpack size of 25
     * Start with 1 Rusty Axe & 3 Health potions
     */
    public Inventory() {
        this(25);
    }

    /**
     * Constructor for Inventory with ability
     * to set the backpack size
     * @param backpackSize - Inventory size
     */
    public Inventory(int backpackSize) {
        if (backpackSize < STARTING_ITEM_COUNT) {
            throw new IllegalArgumentException("Inventory capacity must fit the starting items.");
        }
        this.backpackSize = backpackSize;
        backpack.put("Rusty Axe", 1);
        backpack.put("Health Potion", 3);
    }

    /**
     * Gets the size of the backpack
     * accounting for stacks
     */
    public int sizeOfInventory() {
        int size = 0;
        for (int quantity : backpack.values()) {
            size += quantity;
        }
        return size;
    }

    /**
     * Increase size of Inventory
     * @param amt - Amount to increase by
     */
    public void increaseInventorySize(int amt) {
        if (amt < 0) {
            throw new IllegalArgumentException("Inventory capacity cannot be reduced by a negative amount.");
        }
        backpackSize += amt;
    }

    /**
     * Use method for items(Potions/boost)
     * @param itemName - Item to use
     */
    public int useItem(String itemName) throws Exception {
        if (!backpack.containsKey(itemName)) {
            throw new Exception(itemName + " is not in your inventory!");
        }
        if (itemName.equals("Health Potion") || itemName.equals("Mana Potion")) {
            removeFromInventory(itemName);
            return 20;
        }
        if (itemName.equals("Antidote") || itemName.equals("Energy Restore") || itemName.equals("Burn Heal")) {
            removeFromInventory(itemName);
            return 1;
        }
        return 0;
    }

    public boolean hasItem(String itemName) {
        return backpack.containsKey(itemName);
    }
    /**
     * Select weapon from inventory
     * Only works with 1 item in in backpack
     * @param player
     * @throws Exception
     */
    public String selectWeapon(int index) throws Exception {
        if (index < 1 || index > backpack.size()) {
            throw new Exception("No item at position " + index);
        }
        int currentIndex = 1;
        for (String itemName : backpack.keySet()) {
            if (currentIndex++ == index) {
                if (weapons.Weapon.damageFor(itemName) != null) {
                    System.out.println(itemName + " equipped");
                    return itemName;
                }
                throw new Exception(itemName + " is not a weapon.");
            }
        }
        throw new Exception("No item at position " + index);
    }
    /**
     * Method to add items to Inventory
     * @param itemToAdd - Name of the Weapon/Potion to add
     * @param quantity - Amount to add
     * @throws Exception - Cast message inventory full
     */
    public void addToInventory(String itemToAdd, int quantity) throws Exception {
        int currentSize = sizeOfInventory();
        if (quantity <= 0) {
            throw new Exception("Quantity must be positive.");
        }
        if (quantity > backpackSize - currentSize) {
            throw new Exception("Inventory Is Full!");
        }
        int newQuantity = backpack.getOrDefault(itemToAdd, 0) + quantity;
        backpack.put(itemToAdd, newQuantity);
        System.out.println("Added " + quantity + " more " + itemToAdd + " to your backpack successfully!");
    }

    /**
     * Method to remove items from Inventory
     * @param itemToRemove - Item to remove from inventory
     */
    public void removeFromInventory(String itemToRemove) {
        String matchingName = null;
        for (String itemName : backpack.keySet()) {
            if (itemName.equalsIgnoreCase(itemToRemove)) {
                matchingName = itemName;
                break;
            }
        }
        if (matchingName == null) {
            return;
        }
        int quantity = backpack.get(matchingName);
        if (quantity > 1) {
            backpack.put(matchingName, quantity - 1);
        } else {
            backpack.remove(matchingName);
        }
        System.out.println(itemToRemove + " was successfully removed from backpack!");
    }

    /**
     * Method to view Inventory
     * @param sort - 1 = Default (Weapons, Items)
     * 				 2 = Weapons Only
     * 				 3 = Items Only
     */
    public void viewInventory() {
        if (backpack.isEmpty()) {
            System.out.println("No Items in backpack!\n");
            return;
        }
        int counter = 1;
        for (Map.Entry<String, Integer> item : backpack.entrySet()) {
            System.out.println(counter++ + ".) " + item.getKey() + "(" + item.getValue() + ")");
        }
        System.out.println();
    }
}
