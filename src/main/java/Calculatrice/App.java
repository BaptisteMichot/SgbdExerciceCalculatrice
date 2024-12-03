package Calculatrice;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        HBox hBox = new HBox(5);
        VBox vBox = new VBox(5);

        TextField affichage = new TextField();
        affichage.setEditable(true);

        //Boutons 0 à 9
        Button[] boutonsChiffres = new Button[10];
        for(int i=0; i<=9; i++){

            boutonsChiffres[i] = new Button(String.valueOf(i));
            int chiffre = i;
            boutonsChiffres[i].setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    affichage.appendText(String.valueOf(chiffre));
                }
            });
        }

        //Boutons d'opérations
        
        
        //Placement des boutons dans la grille
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.add(boutonsChiffres[7],0,0);
        grid.add(boutonsChiffres[8],1,0);
        grid.add(boutonsChiffres[9],2,0);
        grid.add(boutonsChiffres[4],0,1);
        grid.add(boutonsChiffres[5],1,1);
        grid.add(boutonsChiffres[6],2,1);
        grid.add(boutonsChiffres[1],0,2);
        grid.add(boutonsChiffres[2],1,2);
        grid.add(boutonsChiffres[3],2,2);
        grid.add(boutonsChiffres[0],1,3);



        vBox.getChildren().addAll(affichage, grid);
        hBox.getChildren().addAll(vBox);
        scene = new Scene(hBox, 200, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}