package calc;

import java.math.BigInteger;
import java.util.Random;

public class Specimen {

    private double firstShare;
    private double secondShare;
    private double thirdShare;
    private Chromosome chromosome;

    public Specimen() {
        generateRandom();

        normalize();
    }

    public Specimen(Chromosome chr) {
        decode(chr);

        normalize();
    }

    private void generateRandom(){
        Random r = new Random();

        firstShare = r.nextInt(100);
        secondShare = r.nextInt(100);
        thirdShare = r.nextInt(100);
    }


    public double calculateX(double a, double b, double c){

        return (firstShare * a + secondShare * b + thirdShare * c)/1000;
    }

    private void decode(Chromosome chr) {
        firstShare = (Double.longBitsToDouble(new BigInteger(
                chr.getGenes().substring(0, Chromosome.oneDoubleLength - 1),
                2).longValue()));
        secondShare = (Double.longBitsToDouble(new BigInteger(
                chr.getGenes().substring(Chromosome.oneDoubleLength, Chromosome.oneDoubleLength * 2 - 1),
                2).longValue()));
        thirdShare = (Double.longBitsToDouble(new BigInteger(
                chr.getGenes().substring(Chromosome.oneDoubleLength*2),
                2).longValue()));
    }

    private void normalize() {

        double temp = firstShare + secondShare + thirdShare;

        firstShare = (firstShare/temp) * 100;
        secondShare = (secondShare/temp) * 100;
        thirdShare = (thirdShare /temp) * 100;

        chromosome = new Chromosome(this);
    }

    public double getFirstShare() {
        return firstShare;
    }

    public double getSecondShare() {
        return secondShare;
    }

    public double getThirdShare() {
        return thirdShare;
    }

    public Chromosome getChromosome() {
        return chromosome;
    }

    public Specimen multiplyWith(Specimen spec) {
        return this.getChromosome().singleOffspring(spec.getChromosome());
    }

    public String toString() {
        String first = ((int)firstShare) + "%";
        String second =((int)secondShare)+ "%";
        String third = ((int)thirdShare)+ "%";

        return "Percentages: " + first + " " + second + " " + third;
    }

    private void debugging(){
        if(firstShare+secondShare+thirdShare < 100) {
            System.out.println("total percent " + (firstShare + secondShare + thirdShare));
        }
    }



}
