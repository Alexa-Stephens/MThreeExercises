import java.text.ParseException;
import java.util.*;

public class Player {

    static Hero hero = new Hero();

    //intro for game
    public static void intro(){
        //give player the information they need to start
        System.out.println("Welcome Hero! You will be delving into the goblin tower!" +
                "\n You start with " + hero.health + "HP, 5 potions each worth 2HP, and " + hero.gold + "GP," +
                "\n " + hero.defense + " temporary HP. You will encounter goblins. Each goblin defeated will earn you 2GP. " +
                "\n When you level up, you will be able to buy potions for 4GP each, and your temporary HP will be restored." +
                "\n Each level will be 10 paces into the dungeon. Get ready!");
    }


    //encounter, returns whether or not hero survived encounter
    public static boolean encounter(Scanner input){
        //create goblin
        Goblin goblin = new Goblin();

        //variable to track if hero dies during encounter
        boolean alive = true;
        int damage;

        //give enounter intro
        System.out.println("\n\nYou are " + hero.steps + " paces into the dungeon, and encounter a goblin! Prepare to fight.");

        //while hero is alive, hero attack and goblin attack
        while(alive && goblin.health > 0){
            //hero attack
            goblin.health -= hero.attack;
            System.out.print("You attack and do " + hero.attack + "dmg. The goblin has " + goblin.health + "HP left.");

            //test if goblin is dead
            if(goblin.health<=0){
                continue;
            }

            //goblin attack
            damage = goblin.attack;
            //test if hero has defense power left, if yes, then take away defense before health
            while(hero.defense>0 && damage>0){

                //decrease defense as appropriate (damage count kept to account for surplus damage)

                    hero.defense--;
                    damage--;

            }
            //take damage total (after defense taken)
            while(damage>0 && hero.health>0){
                hero.health--;
                damage--;
            }

            //display goblin attack
            System.out.println("The goblin slashes at you, and you take " + goblin.attack + " damage." +
                    "\nYou have " + hero.health + "HP and " + hero.defense + " temporary HP.");

            //check if hero is still alive, if dead, indicate with boolean, and end encounter
            if(hero.health <= 0 ){
                System.out.println("The goblin lands one final blow on you, and you crumple to the ground.");
                alive = false;
                return alive;
            }
        }


        //after defeating goblin, increment killcount, add gold and give player stats
        hero.gold += 2;
        hero.slain++;
        System.out.println("\nThe goblin is defeated! You have earned 2 GP." +
                "\n You currently have " + hero.health + "HP, " + hero.gold + "GP, and " + hero.potionCount + " potions left.");

        takePotion(input);

        return alive;

    }

    public static void takePotion(Scanner input){
        //ask player if they want to take a potion
        System.out.println("How many potions would you like to take? Enter '0' for none");
        int potion = input.nextInt();

        //take potion(s), if not, do nothing
        while (potion > 0){

            //test if they have that many health potions
            if(hero.potionCount<potion){
                System.out.println("You don't have that many health potions");
                if(hero.potionCount == 0){
                    potion = 0;
                    break;
                }
                System.out.println("How many potions would you like to take? Enter '0' for none");
            }



            else {
                for(; potion>0; potion--) {
                    //test if they might heal past max health
                    if((hero.health+2) > hero.maxhealth){
                        System.out.println("You cannot take any more potions.");
                        potion = 0;
                    }
                    else {
                        hero.health += 2;
                        hero.potionCount -= 1;
                        hero.potions[hero.potionCount] = 0;
                    }
                }
            }

            //after taking health potions, display current health
            System.out.println("You now have " + hero.health + "HP, " + hero.defense + " temporary HP and " + hero.potionCount
                    + " potions left.");
        }
    }

    public static void levelUp(Scanner input){

        //increase hero level
        hero.level++;
        System.out.println("Congratulations! You are " + hero.steps + " paces into the dungeon.\n" +
                "You have leveled up to Level " + hero.level + ", and have " + hero.gold + "GP");

        //restore defense
        hero.defense = hero.maxdefense;
        System.out.println("You defense has been restored! You now have " + hero.maxdefense + " temporary HP.");

        //ask if user wants to visit potion shop
        System.out.println("You see a potions shop. Would you like to shop for potions?" +
                "\nEnter '1' for yes, enter anything else for no.");

        int next = input.nextInt();

        if (next == 1){
            while (next ==1) {
                System.out.println("The shopkeeper tells you 'Potions cost 4 gold pieces each, same as always. " +
                        "How many do you want?'");

                int numOfPotions = input.nextInt();

                //check if hero has space for that number of potions
                if ((numOfPotions + hero.potionCount) >5){
                    System.out.println("You don't have room for that many potions. You can only carry 5 potions at a time.");
                }
                //check if hero has enough gold
                else if ((numOfPotions * 4 ) > hero.gold){
                    System.out.println("The shopkeeper raises an eyebrow. 'You don't have enough for that many. Try again.");
                }
                else{
                    //subtract cost from hero's gold
                    hero.gold -= numOfPotions *4;

                    //add potions to store
                    hero.potionCount += numOfPotions;
                    for(int i = 0; i<hero.potionCount; i++){
                        hero.potions[i] = 2;
                    }

                    //message from shoppkeeper
                    System.out.println("She smiles and whisks the gold underneath the table. " + numOfPotions + " potions are placed on the table moments later." +
                            "\nYou now have " + hero.potionCount + " total potions.");
                    //clear to continue
                    next = 2;

                }
            }
        }
        else{
            System.out.println("You give the shopkeeper a smile as you pass by her stall. You still have no idea how " +
                    "she managed to set up shop here.");
        }

        takePotion(input);

    }

    //endgame screen, returns 1 for new hero, 2 for exit
    public static int endgame(Scanner input){
        System.out.println("YOU DIED");
        int next = 0;

        //loop until input is correct
        while(next!= 1 && next!=2) {
            System.out.println("Would you like to play again, with your current gold?\n" +
                    "Enter '1' for yes, '2' for no.");
            next=input.nextInt();

            /*
                If the player says yes, create a new character with the same gold they had when they died.
                If the player says no, print out the level the current character reached with the number of goblins slain!
           */

            if (next == 1) {
                //message to player
                System.out.println("You have chosen to enter the tower once more!");

                //reset hero details with current hero gold
                hero = new Hero(hero.gold);

            }

            else if (next == 2) {
                System.out.println("You finished at level " + hero.level + ", and you killed " + hero.slain + " goblins." +
                        "\nNicely done! Thank you for playing!");
            }

            else{
                System.out.println("You have entered an invalid option.");
            }
        }

        return next;
    }


    public static boolean game (){
        Scanner input = new Scanner(System.in);
        boolean play = true;
        boolean alive = true;
        Random random = new Random();
        int encounterChance = 0;
        int encounterLikelihood = 75;   //this determines the threshold for randomly generated percentage for encounter to occur


        //give intro to game
        intro();

        //play game, for as long as the hero is alive
        while(alive) {
            //while alive, take a step
            hero.steps++;

            //if new level reached, then level up and give player chance to go to the potions shop
            if (hero.steps % 10 == 0) {
                levelUp(input);
            }

            //calculate encounter event percentile
            encounterChance = random.nextInt(100);

            //if encounter chance exceeds threshold, encounter occurs
            if (encounterChance > encounterLikelihood) {
                //output of encounter is boolean indicating if hero is still alive
                alive = encounter(input);
            }
        }//hero is dead at end of this

        //give endgame and save output
        int newgame = endgame(input);

        //if player does not want a new game, indicate so to system through boolean
        if (newgame != 1){
            play = false;
        }
        return play;
    }



    //The player is the hero
    public static void main(String[] args) throws ParseException {
        Scanner input = new Scanner(System.in);
        boolean next = true;

        //play game as long as player wishes to continue
        //hero is reset and gold is saved in endgame operation
        while (next){
            next = game();
        }



    }
}
