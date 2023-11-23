package calc;

import java.util.Random;

public class Chromosome {

    private String genes;
    public static final int oneDoubleLength = 64;
    private final int mutationConst = 15; // from 1 to 100, for now;

   public Chromosome(Specimen sp){
       initChromosome(sp);
   }

    public Chromosome(String g){
       this.genes = g;
    }

    private void initChromosome(Specimen sp){

        normalize(sp);
    }

    private void normalize(Specimen sp){
        String firstTemp = (Long.toBinaryString(Double.doubleToRawLongBits(sp.getFirstShare())));
        String secondTemp = (Long.toBinaryString(Double.doubleToRawLongBits(sp.getSecondShare())));
        String thirdTemp = (Long.toBinaryString(Double.doubleToRawLongBits(sp.getThirdShare())));

        int tempLength = firstTemp.length();
        String tempZero = "0";

        for(int i = 0; i<64-tempLength;i++){
            firstTemp += tempZero;
        }

        tempLength = secondTemp.length();
        for(int i = 0; i<64-tempLength;i++){
            secondTemp += tempZero;
        }
        tempLength = thirdTemp.length();
        for(int i = 0; i<64-tempLength;i++){
            thirdTemp += tempZero;
        }

        genes = firstTemp + secondTemp + thirdTemp;

    }

    public Specimen singleOffspring(Chromosome chr){

       String tempFirstParent = genes.substring(0, (int) (oneDoubleLength/2));
       String tempSecondParent = chr.genes.substring((int) (oneDoubleLength * 1.5));
       String A = genes.substring(0, oneDoubleLength/2);
       String B = chr.getGenes().substring(oneDoubleLength/2,oneDoubleLength);
       String C = genes.substring(oneDoubleLength, (int) (oneDoubleLength*1.5));
       String D = chr.getGenes().substring((int) (oneDoubleLength*1.5), oneDoubleLength*2);
       String E = genes.substring(oneDoubleLength*2, (int) (oneDoubleLength*2.5));
       String F = chr.getGenes().substring((int) (oneDoubleLength*2.5), oneDoubleLength*3);
       //Chromosome temp = new Chromosome(tempFirstParent + tempSecondParent);

       Chromosome temp = new Chromosome(A+B+C+D+E+F);

        mutation(temp);

      /*
        int place1 = new Random().nextInt(oneDoubleLength);
        int place2 = new Random().nextInt(oneDoubleLength) + oneDoubleLength;
        int place3 = new Random().nextInt(oneDoubleLength) + oneDoubleLength*2;

        String tempFirst = genes.substring(0, place1-1);
        String tempSecond= chr.genes.substring(place1, place2-1);
        String tempThird = genes.substring(place2, place3);

        Chromosome temp = new Chromosome(tempFirst + tempSecond + tempThird);
        mutation();
 */
        return new Specimen(temp);
    }

    public String getGenes() {
        return genes;
    }

    private Chromosome mutation(Chromosome temp){
       int totalLength = (oneDoubleLength *3);
       Chromosome result = temp;
       Random r = new Random();
       if (r.nextInt(100) <= mutationConst){
                int randomPlace = r.nextInt(totalLength-1)+1;
                String firstHalf = temp.getGenes().substring(0,randomPlace);
                int mutatedGene = 0;
                if(temp.getGenes().charAt(randomPlace) == 0) {
                    mutatedGene = 1;
                }
                String secondHalf = temp.getGenes().substring(randomPlace+1);
                result = new Chromosome( firstHalf + mutatedGene + secondHalf);
            }
    return result;
   }
}
