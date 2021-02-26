import java.util.Random;

public class Goblin {
    /*
    A goblin starts with 5-10 Health points, 2-3 attack power, and 1-2 defense power
     */
    int health;
    int attack;
    int defense;

    Random random = new Random();
    public Goblin(){
        health = random.nextInt(5) + 5;
        attack = random.nextInt(1) + 2;
        defense = random.nextInt(1) + 1 ;

        //add defense power to the health
        health += defense;
    }


}
