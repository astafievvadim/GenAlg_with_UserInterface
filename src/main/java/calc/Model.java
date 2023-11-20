package calc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Model {

    final int numberOfGenerations;
    double first_profitability; //average
    double second_profitability; //average
    double third_profitability; //average

    Specimen[] bestSpecimensPerGen;
    //double first_risk;
    //double third_risk;
    //double second_risk;

    Population population;

    public int getGen(){
        return bestSpecimensPerGen.length;
    }

    public Specimen[] getBestSpecimensPerGen(){
        return bestSpecimensPerGen;
    }

    public double getYofNumber(int number){
        return bestSpecimensPerGen[number].calculateX(first_profitability, second_profitability, third_profitability);
    }

    public Model(double first, double second, double third, int population, int numberOfGenerations){

        this.first_profitability = first;
        this.second_profitability = second;
        this.third_profitability = third;

        this.numberOfGenerations = numberOfGenerations;

        this.bestSpecimensPerGen = new Specimen[numberOfGenerations];

        this.population = new Population(population);

        simulation();

    }

    private void simulation(){
        for(int i = 0; i < numberOfGenerations; i++){
            multiplyWithBest();
            bestSpecimensPerGen[i] = population.findBestOne(first_profitability,second_profitability,third_profitability);
        }
    }

    public void printBestAtGeneration(int genNumber){
        Specimen best = bestSpecimensPerGen[genNumber-1];

        String partA = best.toString();

        System.out.println(partA);
    }


    private void multiplyWithBest() {

        Specimen parentA = population.findBestOne(first_profitability, second_profitability, third_profitability);

        List<Specimen> oldPop = population.getCurrentPopulation();
        List<Specimen> kids = new ArrayList<>();

        for (int i = 0; i < population.getLength(); i = i+2) {
            kids.add(parentA.multiplyWith(population.getAt(i)));
            kids.add(population.getAt(i).multiplyWith(parentA));
        }

        kids.addAll(oldPop);
        kids.sort(Comparator.comparing(a -> a.calculateX(first_profitability, second_profitability, third_profitability)));
        Collections.reverse(kids);

        kids.subList(oldPop.size(), kids.size()).clear();

        population.replacePool(kids);
    }


    public void printResult(){

        Specimen best = population.findBestOne(first_profitability, second_profitability,third_profitability);

        String partA = best.toString();

        System.out.println(partA);
    }


    public String getResult(){

        Specimen best = population.findBestOne(first_profitability, second_profitability,third_profitability);

        return best.toString();

    }

    public double getY(){
        Specimen temp = population.findBestOne(first_profitability, second_profitability, third_profitability);
        return 5 + temp.calculateX(first_profitability,second_profitability,third_profitability);
    }

    public double getX(){
        return bestSpecimensPerGen.length + 5;
    }
}

