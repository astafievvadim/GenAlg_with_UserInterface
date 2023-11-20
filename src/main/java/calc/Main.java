package calc;

public class Main {

    /*
    риск считается через матрицу ковариации
    Задача Марковица - загуглить
    Доля один доля два коэффицент ковариации
     */

    public static void main(String[] args) {

        Model c = new Model(
                1, 50, 8,   // доходности
                15,                          // число особей в популяции
                100                         // поколения
                );                       // капитал

        c.printBestAtGeneration(1);
        System.out.println("---");

        c.printResult();
    }
}