import java.util.Random;

public class Hero{
     /*
    Each new hero starts with random max health points between 20 to 30.
    Each new hero will start with a random attack power between 1 to 3.
    Each new hero will start with a random defense power between 1-5.
    Each new hero will start with an array of potions (int) with a max of 5 slots. Each slot set to 2.
    Each new hero will start with 0 gold.
    The hero will take a step, and possibly encounter a 'goblin.'
    After every 10 steps:
        The hero advances one level in the game.
        The hero may visit the "potion shop" to buy more potions for 4 gold each.

     */
    static Random random = new Random();

    int health;
    int maxhealth;
    int attack;
    int maxdefense;
    int defense;
    int[] potions = new int[5];
    int potionCount = 5;
    int gold;
    int steps;
    int level;
    int slain;

    public  Hero() {
        //set beginning values for the hero
        maxhealth = random.nextInt(10) + 20;
        health = maxhealth;
        attack = random.nextInt(2) + 1;
        maxdefense = random.nextInt(4) + 1;
        defense = maxdefense;
        gold = 0;
        steps = 1;
        level = 1;
        slain = 0;

        //set all potion values to 2
        for(int i =0; i<5; i++){
            potions[i] = 2;
        }
    }

    //if  player chooses to play again with another hero, then save the gold to the new hero
    public  Hero(int gold) {
        //set beginning values for the hero
        maxhealth = random.nextInt(10) + 20;
        health = maxhealth;
        attack = random.nextInt(2)+1;
        maxdefense = random.nextInt(4) + 1;
        defense = maxdefense;
        this.gold = gold;   //set gold to previous amount
        steps = 1;
        level = 1;
        slain = 0;

        //set all potion values to 2
        for(int i =0; i<5; i++){
            potions[i] = 2;
        }
    }
}
