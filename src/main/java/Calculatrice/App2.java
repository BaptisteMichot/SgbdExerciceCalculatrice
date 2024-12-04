package Calculatrice;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class App2 extends Application {

    private static Scene scene;
    private String operation = "";
    private double total = 0;
    private double currentOperand = 0;

    @Override
    public void start(Stage stage) throws IOException {

        // Ecran de la calculatrice
        TextField affichage = new TextField();
        affichage.setPrefWidth(415);
        affichage.setPrefHeight(75);
        affichage.setStyle("-fx-font-size: 35px;");
        affichage.setAlignment(Pos.BASELINE_RIGHT);
        affichage.textProperty().addListener((observable, ancienAffichage, nouvelAffichage) -> {
            // Assurer que seul les chiffres et le point sont affichés
            if (!nouvelAffichage.matches("\\d*\\.?\\d*")) { 
                affichage.setText(ancienAffichage);
            }
        });

        // Création des boutons
        Button[] chiffres = new Button[10];
        for (int i = 0; i <= 9; i++) {
            chiffres[i] = new Button(String.valueOf(i));
            chiffres[i].setStyle("-fx-font-size: 35px;");
            chiffres[i].setPrefWidth(100);
            chiffres[i].setPrefHeight(100);
        }

        Button virgule = new Button(".");
        virgule.setStyle("-fx-font-size: 35px;");
        virgule.setPrefWidth(100);
        virgule.setPrefHeight(100);

        Button supprimer = new Button("←");
        supprimer.setStyle("-fx-font-size: 35px;");
        supprimer.setPrefWidth(100);
        supprimer.setPrefHeight(100);

        Button plus = new Button("+");
        plus.setStyle("-fx-font-size: 35px;");
        plus.setPrefWidth(100);
        plus.setPrefHeight(100);

        Button moins = new Button("-");
        moins.setStyle("-fx-font-size: 35px;");
        moins.setPrefWidth(100);
        moins.setPrefHeight(100);

        Button fois = new Button("*");
        fois.setStyle("-fx-font-size: 35px;");
        fois.setPrefWidth(100);
        fois.setPrefHeight(100);

        Button divise = new Button("/");
        divise.setStyle("-fx-font-size: 35px;");
        divise.setPrefWidth(100);
        divise.setPrefHeight(100);

        Button egal = new Button("=");
        egal.setStyle("-fx-font-size: 35px;");
        egal.setPrefWidth(205);
        egal.setPrefHeight(100);

        Button ac = new Button("AC");
        ac.setStyle("-fx-font-size: 35px;");
        ac.setPrefWidth(205);
        ac.setPrefHeight(100);

        // Placement des boutons dans la grille
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.add(chiffres[7], 0, 0);
        grid.add(chiffres[8], 1, 0);
        grid.add(chiffres[9], 2, 0);
        grid.add(chiffres[4], 0, 1);
        grid.add(chiffres[5], 1, 1);
        grid.add(chiffres[6], 2, 1);
        grid.add(chiffres[1], 0, 2);
        grid.add(chiffres[2], 1, 2);
        grid.add(chiffres[3], 2, 2);
        grid.add(chiffres[0], 1, 3);
        grid.add(divise, 3, 0);
        grid.add(fois, 3, 1);
        grid.add(moins, 3, 2);
        grid.add(plus, 3, 3);
        grid.add(virgule, 2, 3);
        grid.add(supprimer, 0, 3);

        // Gestion de l'affichage dans la fenêtre
        HBox hBoxBas = new HBox(5, ac, egal);
        VBox vBox = new VBox(5, affichage, grid, hBoxBas);
        HBox hBox = new HBox(5, vBox);
        vBox.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);
        scene = new Scene(hBox, 500, 650);
        stage.setScene(scene);
        stage.show();

        // Préparation des boutons chiffres
        for (int i = 0; i <= 9; i++) {
            int chiffre = i;
            chiffres[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    affichage.appendText(String.valueOf(chiffre));
                }
            });
        }

        virgule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                affichage.appendText(virgule.getText());
            }
        });

        supprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String texteAffichage = affichage.getText();
                if (texteAffichage != null && !texteAffichage.isEmpty()) {
                    affichage.setText(texteAffichage.substring(0, texteAffichage.length() - 1));
                }
            }
        });

        // Gestion des boutons d'opération
        plus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!affichage.getText().isEmpty()) {
                    currentOperand = Double.parseDouble(affichage.getText());
                    if (operation.isEmpty()) {
                        total = currentOperand;
                    } else {
                        performOperation();
                    }
                }
                operation = "+"; // On garde l'opération pour le prochain chiffre
                affichage.setText("");
            }
        });

        moins.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!affichage.getText().isEmpty()) {
                    currentOperand = Double.parseDouble(affichage.getText());
                    if (operation.isEmpty()) {
                        total = currentOperand;
                    } else {
                        performOperation();
                    }
                }
                operation = "-";
                affichage.setText("");
            }
        });

        fois.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!affichage.getText().isEmpty()) {
                    currentOperand = Double.parseDouble(affichage.getText());
                    if (operation.isEmpty()) {
                        total = currentOperand;
                    } else {
                        performOperation();
                    }
                }
                operation = "*";
                affichage.setText("");
            }
        });

        divise.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!affichage.getText().isEmpty()) {
                    currentOperand = Double.parseDouble(affichage.getText());
                    if (operation.isEmpty()) {
                        total = currentOperand;
                    } else {
                        performOperation();
                    }
                }
                operation = "/";
                affichage.setText("");
            }
        });

        egal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!affichage.getText().isEmpty()) {
                    currentOperand = Double.parseDouble(affichage.getText());
                    performOperation();
                }
                affichage.setText(String.valueOf(total));
                total = 0; // Réinitialisation du total après avoir affiché le résultat
            }
        });

        ac.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                total = 0; // Réinitialisation complète
                affichage.setText("");
            }
        });
    }

    private void performOperation() {
        switch (operation) {
            case "+":
                total += currentOperand;
                break;
            case "-":
                total -= currentOperand;
                break;
            case "*":
                total *= currentOperand;
                break;
            case "/":
                if (currentOperand != 0) {
                    total /= currentOperand;
                } else {
                    total = 0; // Gestion de la division par 0
                }
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}