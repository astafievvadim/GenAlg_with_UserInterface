package calc;

import java.lang.reflect.Parameter;
import java.util.*;

public class Model {

    private final int numberOfGenerations;
    private final double first_profitability; //average
    private final double second_profitability; //average
    private final double third_profitability; //average


    Specimen[] bestSpecimensPerGen;

    Double[] firnessPerGeneration;

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
            //multiplyWithBest();
            multiplyTournament();
            bestSpecimensPerGen[i] = population.findBestOne(first_profitability,second_profitability,third_profitability);
        }
    }

    private List<Specimen> getElitism(List<Specimen> kids, Population parents){

        List<Specimen> temp = new ArrayList<>();

        for(int i =0;i<kids.size()/10;i++){
            temp.add(parents.findBestOne(first_profitability,second_profitability,third_profitability));
            parents.remove(0);
        }

        kids.sort(Comparator.comparing(a -> a.calculateX(first_profitability, second_profitability, third_profitability)));
        Collections.reverse(kids);

        for(int i = 1; i< temp.size(); i++){
            kids.remove(kids.size() - i);
        }
        kids.addAll(temp);

        return kids;
        /*
        List<Specimen> Elitism = new ArrayList<>();
        Population temp = population;


        for(int i = 0; i < population.getLength()/10; i++){
            Elitism.add(population.findBestOne(first_profitability, second_profitability, third_profitability));
            temp.remove(0);
        }
        return Elitism;
         */
    }

    public void printBestAtGeneration(int genNumber){
        Specimen best = bestSpecimensPerGen[genNumber];

        String partA = best.toString();
        System.out.println(partA + " - " + best.calculateX(first_profitability,second_profitability,third_profitability));
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

    private void multiplyTournament() {


        List<Specimen> oldPop = population.getCurrentPopulation();
        List<Specimen> populative = new ArrayList<>();
        List<Specimen> kids = new ArrayList<>();

        int r;

        for(int i = 0; i<population.getLength(); i= i+2){

            r = new Random().nextInt(population.getLength());
            Specimen parentA = oldPop.get(r);

            r = new Random().nextInt(population.getLength());
            Specimen parentB = oldPop.get(r);

            if(parentB.calculateX(first_profitability,second_profitability,third_profitability) >= parentA.calculateX(first_profitability,second_profitability,third_profitability)){
                populative.add(parentB);
            }
            else{
                populative.add(parentA);
            }
        }

        for(int i = 0; i<populative.size(); i++){

            int a = new Random().nextInt(populative.size());
            int b = new Random().nextInt(populative.size());

            kids.add(populative.get(a).multiplyWith(populative.get(b)));
            kids.add(populative.get(b).multiplyWith(populative.get(a)));
        }

        //ELITISM

        population.replacePool(getElitism(kids,population));
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
        return temp.calculateX(first_profitability,second_profitability,third_profitability);
    }

    public double getX(){
        return bestSpecimensPerGen.length + 5;
    }
}

