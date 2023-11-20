package calc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Population {
    private List<Specimen> pool;

    public Population(int x) {
        pool = new ArrayList<>(x);
        fill(x);
    }

    public void fill(int x){
        for (int i = 0; i < x; i++){
            pool.add(new Specimen());
        }
    }

    public Specimen findBestOne(double a, double b, double c){

        List<Specimen> temp = pool;

        temp.sort(Comparator.comparing(abs -> abs.calculateX(a, b, c)));
        Collections.reverse(temp);

        return temp.get(0);
    }

    public Specimen getAt(int number){
        if(number > pool.size()){
            return null;
        }
        return pool.get(number);
    }

    public Specimen findSecondBest(double a, double b, double c){
        List<Specimen> temp = pool;

        temp.sort(Comparator.comparing(abs -> abs.calculateX(a, b, c)));
        Collections.reverse(temp);

        return temp.get(1);
    }

    public int getLength(){
        return pool.size();
    }

    public List<Specimen> getCurrentPopulation(){
        return pool;
    }

    public void replacePool(List<Specimen> a){
        this.pool = a;
    }

    public void printPopulation(){
        for(int i = 0; i < pool.size(); i++) {
            System.out.println(pool.get(i));
        }
    }
}
