import java.util.Random;

public class Individual {
    public String chromosome;
    public int fitness;
    public String target = "Deep Learning 2022";  //Chromosome that has 18 genes -> The password to be cracked
    public String genes = "abcçdefgğhıijklmnoöpqrsştuüvwxyzABCÇDEFGĞHIİJKLMNOÖPQRSŞTUÜVWXYZ 1234567890, .-;:_!\"#%&/()=?@${[]}"; //-> Turkish alphabet
    Random rand = new Random();

    public Individual(String chromosome) {
        this.chromosome = chromosome;
        this.fitness = this.calFitness();
    }

    public Individual() {
    }

    //Fitness value Calculation
    public int calFitness() {
        int tempFitness = 0;
        int len = target.length();
        for (int i = 0; i < len; i++) {
            if (chromosome.charAt(i) != target.charAt(i)) {
                tempFitness++;
            }
        }
        return tempFitness;
    }

    //Mutation -> Find random genes to mutations
    public char mutatedGenes() {
        int i = rand.nextInt(genes.length());
        char temp = genes.charAt(i);
        return temp;
    }

    //First chromosomes with 18 genes long
    public String createGnome() {
        int len = target.length();
        String gnome = "";
        for (int i = 0; i < len; i++) {
            gnome += mutatedGenes();
        }
        return gnome;
    }

    //Crossover -> ratio = %90
    public Individual mate(Individual par2) {
        String child_chromosome = "";
        int len = chromosome.length();
        for (int i = 0; i < len; i++) {
            float p = rand.nextFloat();

            // if prob is less than 0.45, insert gene from parent 1
            if (p < 0.45) {
                child_chromosome += chromosome.charAt(i);

                // if prob is between 0.45 and 0.90, insert gene from parent 2
            } else if (p < 0.90) {
                child_chromosome += par2.chromosome.charAt(i);

                // otherwise insert random gene(mutate) for maintaining diversity
            } else {
                child_chromosome += mutatedGenes();
            }
        }

        Individual child_Individual = new Individual(child_chromosome);
        return child_Individual;
    }

    public int compareTo(Individual compareStu) {
        int compareFitness = compareStu.fitness;
        return this.fitness - compareFitness;

    }
}

