import java.util.Scanner;
import java.util.Random;

public class Hammurabi {
    private int year = 1;
    private int population = 100;
    private int bushels = 2800;
    private int acres = 1000;
    private int landPrice = 20;
    private int totalDeaths = 0;

    private final Scanner input = new Scanner(System.in);
    private final Random random = new Random();


    void playGame() {
        ;

        while (year <= 10) {

            int acresToBuy = ("How many acres do you wish to buy? ", 0, bushels / landPrice);
            acres += acresToBuy;
            bushels -= acresToBuy * landPrice;

            int acresToSell = ("How many acres do you wish to sell? ", 0, acres);
            acres -= acresToSell;
            bushels += acresToSell * landPrice;

            int bushelsToFeed = ("How many bushels do you wish to feed your people? ", 0, bushels);
            bushels -= bushelsToFeed;

            int acresToPlant = ("How many acres do you wish to plant with seed? ", 0, Math.min(acres, population * 10));
            int seedRequired = acresToPlant / 2;
            if (seedRequired > bushels) {
                System.out.println("Not enough grain to plant that much. Planting fewer acres.");
                acresToPlant = bushels * 2;
                seedRequired = bushels;
            }
            bushels -= seedRequired;
        }
    }
}