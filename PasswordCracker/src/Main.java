import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Random rand = new Random();

        Individual individual = new Individual();
        String gnome;
        int populationSize = 1000; //Chromosome count
        int generation = 1;  //Current Generation
        ArrayList<Individual> population = new ArrayList<>();

        boolean found = false;

        //Creating first generation
        for (int i = 0; i < populationSize; i++) {
            gnome = individual.createGnome();
            population.add(new Individual(gnome));
        }
        while (!found) {
            population.sort(Individual::compareTo); //Sort Individuals

            //Asked value found
            if (population.get(0).fitness <= 0) {
                found = true;
                break;
            }
            ArrayList<Individual> newGeneration = new ArrayList<>();

            // Perform Elitism, that mean 10% of fittest population goes to the next generation
            int s = (int) ((10 * populationSize) / 100);
            for (int i = 0; i < s; i++) {
                newGeneration.add(population.get(i));
            }

            // From 50% of fittest population, Individuals will mate to produce offspring
            s = (int) ((90 * populationSize) / 100);
            for (int i = 0; i < s; i++) {
                int r = rand.nextInt(50);
                Individual parent1 = population.get(r);
                r = rand.nextInt(50);
                Individual parent2 = population.get(r);
                Individual offspring = parent1.mate(parent2);
                newGeneration.add(offspring);
            }

            population = newGeneration;
            System.out.print("Generation:" + generation + "\t");
            System.out.print("String:" + population.get(0).chromosome + "\t");
            System.out.print("Fitness:" + population.get(0).fitness + "\n");
            generation++;
        }

        System.out.print("Generation:" + generation + "\t");
        System.out.print("String:" + population.get(0).chromosome + "\t");
        System.out.print("Fitness:" + population.get(0).fitness + "\n");

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Duration : " + duration / Math.pow(10, 9) + " sn");

    }
}
