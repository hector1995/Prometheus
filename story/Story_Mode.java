package story;

import battle_system.Battle_System;
import character.Character_Class;
import enemy.Enemy;
import enemy.Enemy_Giant;
import enemy.Enemy_Goblin;
import enemy.Enemy_Grunt;
import enemy.Enemy_Necromancer;
import enemy.Enemy_Siren;
import java.util.Scanner;

public class Story_Mode {
    private final Scanner input;
    private final Battle_System battle = new Battle_System();
    private boolean completed;
    private int currentChapter = 1;

    public Story_Mode(Scanner input) {
        this.input = input;
    }

    public void playStory(Character_Class player) throws Exception {
        if (completed) {
            System.out.println("You have already completed the story.");
            return;
        }
        if (player.getHealth() <= 0) {
            System.out.println("You cannot continue while your character has no health.");
            return;
        }

        while (currentChapter <= 5) {
            Enemy enemy;
            switch (currentChapter) {
                case 1 -> {
                    System.out.println("Chapter 1: The Road to Prometheus");
                    System.out.println("On the road to the city, a Grunt jumps from the roadside and blocks your way.");
                    enemy = new Enemy_Grunt();
                }
                case 2 -> {
                    System.out.println("Chapter 2: The Cabin in the Woods");
                    System.out.println("A Goblin has taken over an abandoned cabin and attacks when you search it for supplies.");
                    enemy = new Enemy_Goblin();
                }
                case 3 -> {
                    System.out.println("Chapter 3: The Fogbound Lake");
                    System.out.println("A figure appears over the lake at dusk. As you row closer, a Siren rises from the fog.");
                    enemy = new Enemy_Siren();
                }
                case 4 -> {
                    System.out.println("Chapter 4: The Mountain Cave");
                    System.out.println("You shelter from a storm in a cave, only to discover the bones and footprints of a Giant.");
                    enemy = new Enemy_Giant();
                }
                default -> {
                    System.out.println("Chapter 5: The Necromancer's Keep");
                    System.out.println("Beyond the desert, a Necromancer guards the source of the darkness spreading across the land.");
                    enemy = new Enemy_Necromancer();
                }
            }

            if (!battle.battleSystem(player, enemy)) {
                System.out.println("Game over. Your journey ends here.");
                return;
            }

            int reward = currentChapter * 100;
            player.setMoney(player.getMoney() + reward);
            System.out.println("You earned $" + reward + ". You now have $" + player.getMoney() + ".");
            currentChapter++;
            if (currentChapter > 5) {
                completed = true;
                System.out.println("With the Necromancer defeated, peace returns to Prometheus. Story complete!");
                return;
            }

            System.out.println("Continue to Chapter " + currentChapter + "? (Y = continue, N = return to main menu)");
            if (!input.nextLine().trim().equalsIgnoreCase("Y")) {
                System.out.println("Your progress is saved. Choose Story Mode again to continue at Chapter " + currentChapter + ".");
                return;
            }
        }
    }
}
