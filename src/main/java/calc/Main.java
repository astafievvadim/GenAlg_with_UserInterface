package calc;

import com.ga.genalg_with_userinterface.HelloApplication;

import static javafx.application.Application.launch;

public class Main {

    /*
    риск считается через матрицу ковариации
    Задача Марковица - загуглить
    Доля один доля два коэффицент ковариации
     */


        //new HelloApplication();
        public static void main(String[] args) {

            System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(100)));
            System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(-100)));

            HelloApplication t = new HelloApplication();
            t.startApplication();

        }
}