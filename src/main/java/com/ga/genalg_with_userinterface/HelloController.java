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
import javafx.scene.text.Text;

import java.net.URL;

public class HelloController {
    public TextField FirstInput;
    public TextField SecondInput;
    public TextField ThirdInput;
    public TextField PopulationInput;
    public TextField GenerationsInput;
    public Text AnswerOutput;
    public LineChart Graph;
    public Button StartButton;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
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

        //manageGraph(c);

        AnswerOutput.setText(c.getResult());

        c.printResult();
    }

    private void manageGraph(Model c){

        LineChart temp = null;

        ObservableList list = FXCollections.observableArrayList(c.getBestSpecimensPerGen());

        ObservableList<XYChart.Data<Number, Number>> data = FXCollections.<XYChart.Data<Number, Number>>observableArrayList();
        for (int i = 0; i < c.getBestSpecimensPerGen().length; i++){
            data.add(new XYChart.Data<>(i, c.getYofNumber(i)));
        }

        XYChart.Series series = new XYChart.Series(data);

        temp.getData().add(series);

        Graph = temp;

        Graph.layout();
    }
}