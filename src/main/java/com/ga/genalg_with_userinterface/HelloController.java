package com.ga.genalg_with_userinterface;

import calc.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;

public class HelloController {
    public TextField FirstInput;
    public TextField SecondInput;
    public TextField ThirdInput;
    public TextField PopulationInput;
    public TextField GenerationsInput;
    public Text AnswerOutput;
    //public LineChart Graph;
    public Button StartButton;
    public AnchorPane PutHere;

    @FXML
    public void OnStartButtonAction(ActionEvent event) {

        double first = Double.parseDouble(FirstInput.getText());
        double second = Double.parseDouble(SecondInput.getText());
        double third = Double.parseDouble(ThirdInput.getText());

        int generations = Integer.parseInt(GenerationsInput.getText());
        int population = Integer.parseInt(PopulationInput.getText());

        Model c = new Model(
                first,
                second,
                third,
                population,
                generations
        );

        manageGraph(c);

        AnswerOutput.setText(c.getResult());

        c.printBestAtGeneration(generations/4);

        c.printBestAtGeneration(generations/3);

        c.printBestAtGeneration(generations/2);
        c.printResult();
    }

    private void manageGraph(Model c){

        XYChart.Series<Integer,Double> series = new XYChart.Series<Integer,Double>();
        series.setName("Fitness per generation");

        for(int i = 0; i < c.getBestSpecimensPerGen().length; i++){
            series.getData().add(new XYChart.Data<>(i,c.getYofNumber(i)/c.getY()));
        }

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        LineChart Graph = new LineChart<Number,Number>(xAxis, yAxis);

        Graph.getYAxis().setAutoRanging(true);
        Graph.getXAxis().setAutoRanging(true);
        Graph.setCreateSymbols(false);
        Graph.getData().add(series);
        Graph.getCreateSymbols();
        PutHere.getChildren().clear();
        PutHere.getChildren().add(Graph);
        Graph.layout();

        /*
        String funcs[] = {"y(x) = sin(X)","y(x) = cos(x) - 2 * sin(x)","y(x) = sin(x*x)"};
        functionChooser.getItems().setAll(funcs);
        functionChooser.getSelectionModel().selectFirst();

        lnChart = new LineChart<Number,Number>(xAxis,yAxis);
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Portfolio 1");
        series1.getData().add(new XYChart.Data(0, 23));
        series1.getData().add(new XYChart.Data(1, 14));
        series1.getData().add(new XYChart.Data(2, 15));
        series1.getData().add(new XYChart.Data(3, 24));
        series1.getData().add(new XYChart.Data(4, 34));

        lnChart.getData().add(series1);


         */
        }
    }

