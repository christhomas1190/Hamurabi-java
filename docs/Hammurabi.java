package hammurabi.docs;

import java.io.IOException;
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

    private void playGame() {
        inrto();
        while (year <= 10) {
            kingdomUpdate();
            int acresToBuy = userInput("How many acres do you wish to buy?", 0, bushels / landPrice);
            acres += acresToBuy;
            bushels -= acresToBuy * landPrice;

            int acresToSell = userInput("How much land do you wish to sell?", 0, acres);
            acres -= acresToSell;
            bushels += acresToSell * landPrice;

            int bushelsToFeed = userInput("How many bushels would you like feed your people?(each person needs 20 per year to survive)", 0, bushels);
            bushels -= bushelsToFeed;
            peopleStarving(bushelsToFeed);


            int acresToPlant = userInput("How many acres do you want to plant on", 0, Math.min(acres, population * 10));
            int seedsReq = acresToPlant / 2;
            if (seedsReq > bushels) {
                System.out.println("Not enough grain to plant, since you can't add ill do it for you...");
                acresToPlant = bushels * 2;
                seedsReq = bushels;
            }
            bushels -= seedsReq;


            harvest(acresToPlant);

            landPrice = 17 + random.nextInt(6);
            year++;

        }
        endGame();

    }


    private void inrto() {
        System.out.println("HAMURABI: TRY YOUR HAND AT GOVERNING ANCIENT WILMINGTON");

    }

    private void kingdomUpdate() {
        System.out.printf("Year: %d\nPopulation: %d\nBushels in storage: %d\nAcres owned: %d\nLand price: %d bushels per acre\n\n",
                year, population, bushels, acres, landPrice);
    }

    private int userInput(String message, int min, int max) {
        int inputVal = -1;
        while (inputVal < min || inputVal > max) {
            System.out.println(message);
            while (!input.hasNextInt()) {
                input.next();// this clears an invalid input
            }
            inputVal = Integer.parseInt(input.next());
            System.out.println(message);

        }
        return inputVal;
    }

    private void harvest(int planted) {
        int yeild = 1 + random.nextInt(6);
        int harvested = planted * yeild;
        bushels += harvested;
    }

    private void rats() {
    }

    private void plague() {
    }

    private void peopleStarving(int bushelsToFeed) {
        int foodNeeded= population*20;//everyone needs 20 bushels
        if(bushelsToFeed<foodNeeded){
            int peopleFed =bushelsToFeed/20;
            int deaths = population-peopleFed;
            totalDeaths+=deaths;
            population =peopleFed;
            System.out.printf("%d people starved this year.\n", deaths);
        }else{
            System.out.println("Everyone was fed");
        }
    }
    private void showStats(){
        System.out.printf("\n--- Statistics after year %d ---\nPopulation: %d\nBushels: %d\nAcres: %d\nLand price: %d\nTotal Deaths: %d\n\n",
                year, population, bushels, acres, landPrice, totalDeaths);
    }

    private void endGame() {
        System.out.println("\nIn your 10-year reign:");
        System.out.printf("Total deaths: %d\nFinal population: %d\nFinal bushels: %d\nFinal acres: %d\n",
                totalDeaths, population, bushels, acres);
    }


    public static void main(String[] args) throws IOException {
        Hammurabi game = new Hammurabi();
        game.playGame();
    }
    public int getYear() {
        return year;
    }

    public int getPopulation() {
        return population;
    }

    public int getBushels() {
        return bushels;
    }

    public int getAcres() {
        return acres;
    }

    public int getLandPrice() {
        return landPrice;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }
}