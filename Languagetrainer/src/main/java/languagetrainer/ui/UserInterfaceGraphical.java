/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package languagetrainer.ui;

/**
 *
 * @author lauri
 */
import languagetrainer.domain.*;
import java.util.ArrayList;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;


public class UserInterfaceGraphical extends Application {
    
    private Scene exerciseOptionsScene;
    
    @Override
    public void start(Stage primaryStage) {
        
        // Create task list
        ArrayList<String> files = new ArrayList<>();
        files.add("espanjanverbilista.csv");
        TaskList taskList = new TaskList(files);
        ArrayList<Task> tasks = taskList.getTasks();
        
        // Find out / specify what options are available
        ArrayList<Language> questionLanguages = taskList.getQuestionLanguages();
        ArrayList<Language> answerLanguages = taskList.getAnswerLanguages();
        ArrayList<WordType> types = taskList.getTypes();
        ArrayList<WordTense> tenses = taskList.getTenses();
        ExerciseOrder order[] = { ExerciseOrder.ASCENDING, ExerciseOrder.DESCENDING, ExerciseOrder.RANDOM };
        
        // Populate visible option lists using translations
        ResourceBundle translations = ResourceBundle.getBundle("Translations");
        ArrayList<String> options1 = new ArrayList<>();
        for (Language language: questionLanguages) {
            options1.add(translations.getString(language.toString()));
        }
        
        // Exercise options scene
        BorderPane exerciseOptionsPane = new BorderPane();
        VBox centerPane = new VBox(12);
        centerPane.setSpacing(10);
        Label mainText = new Label("Valitse asetukset uudelle harjoitukselle.");
        Label optionsText1 = new Label("Kysymyksien kieli");
        ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList(options1));
        cb.setValue(options1.get(0));
        Label optionsText2 = new Label("Vastauksien kieli");
        Label optionsText3 = new Label("Sanaluokat");
        Label optionsText4 = new Label("Aikamuodot");
        Label optionsText5 = new Label("Tehtävien määrä");
        Label optionsText6 = new Label("Tehtävien järjestys");
        Label statusText = new Label("");
        Button startButton = new Button("Aloita uusi harjoitus");
        startButton.setOnAction((event) -> {
            String selection = cb.getValue();
            Language selectedQuestionLanguage = questionLanguages.get(options1.indexOf(selection));
            statusText.setText(selectedQuestionLanguage.toString());
        });
        
        centerPane.getChildren().addAll(optionsText1, cb, optionsText2, optionsText3, optionsText4, optionsText5, optionsText6, statusText);
        exerciseOptionsPane.setTop(mainText);
        exerciseOptionsPane.setCenter(centerPane);
        exerciseOptionsPane.setBottom(startButton);
        exerciseOptionsScene = new Scene(exerciseOptionsPane, 300, 250);
        
        
        
        // Configure and show primaryStage
        primaryStage.setTitle("Language Trainer");
        primaryStage.setScene(exerciseOptionsScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(UserInterfaceGraphical.class);
    }
}
