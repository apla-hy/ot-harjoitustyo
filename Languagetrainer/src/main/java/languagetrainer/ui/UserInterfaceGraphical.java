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
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;


public class UserInterfaceGraphical extends Application {
    
    private Scene exerciseOptionsScene;
    
    @Override
    public void start(Stage primaryStage) {
        
        // Create task list
        ArrayList<String> files = new ArrayList<>();
        files.add("espanjanverbilista.csv");
        files.add("taskListForTestingOptions.csv");
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
        // Question languages
        ArrayList<String> options1 = new ArrayList<>();
        for (Language language: questionLanguages) {
            options1.add(translations.getString(language.toString()));
        }
        // Answer languages
        ArrayList<String> options2 = new ArrayList<>();
        for (Language language: answerLanguages) {
            options2.add(translations.getString(language.toString()));
        }
        // Word types
        ArrayList<String> options3 = new ArrayList<>();
        for (WordType type: types) {
            options3.add(translations.getString(type.toString()));
        }
                
        // Exercise options scene
        BorderPane exerciseOptionsPane = new BorderPane();
        VBox centerPane = new VBox(12);
        HBox bottomPane = new HBox(12);
        centerPane.setSpacing(10);
        Label mainText = new Label("Valitse asetukset uudelle harjoitukselle.");
        
        // Question language
        Label optionsText1 = new Label("Kysymyksien kieli");
        ChoiceBox<String> cbQuestionLanguage = new ChoiceBox<String>(FXCollections.observableArrayList(options1));
        cbQuestionLanguage.setValue(options1.get(0));
        
        // Answer language
        Label optionsText2 = new Label("Vastauksien kieli");
        ChoiceBox<String> cbAnswerLanguage = new ChoiceBox<String>(FXCollections.observableArrayList(options2));
        cbAnswerLanguage.setValue(options2.get(0));
        
        // Word types
        ArrayList<CheckBox> typeBoxes = new ArrayList<>();
        Label optionsText3 = new Label("Sanaluokat");
        for (String option: options3) {
            CheckBox cb = new CheckBox(option);
            typeBoxes.add(cb);
        }
        typeBoxes.get(0).setSelected(true);
                
        Label optionsText4 = new Label("Aikamuodot");
        Label optionsText5 = new Label("Tehtävien määrä");
        Label optionsText6 = new Label("Tehtävien järjestys");
        Label statusText = new Label("");
        Button startButton = new Button("Aloita uusi harjoitus");
        startButton.setOnAction((event) -> {
            String selection = cbQuestionLanguage.getValue();
            Language selectedQuestionLanguage = questionLanguages.get(options1.indexOf(selection));
            selection = cbAnswerLanguage.getValue();
            Language selectedAnswerLanguage = answerLanguages.get(options2.indexOf(selection));
            ArrayList<WordType> selectedTypes = new ArrayList<>();
            for (int i = 0; i < typeBoxes.size(); i++) {
                if (typeBoxes.get(i).selectedProperty().getValue()) {
                    selectedTypes.add(types.get(i));
                }
            }
            statusText.setText(selectedQuestionLanguage.toString() + " " + selectedAnswerLanguage.toString() + " " + selectedTypes);
        });
        
        centerPane.getChildren().addAll(optionsText1, cbQuestionLanguage, optionsText2, cbAnswerLanguage, optionsText3);
        for (CheckBox cb: typeBoxes) {
            centerPane.getChildren().add(cb);
        }
        
        bottomPane.getChildren().addAll(startButton, statusText);
        
        exerciseOptionsPane.setTop(mainText);
        exerciseOptionsPane.setCenter(centerPane);
        exerciseOptionsPane.setBottom(bottomPane);
        exerciseOptionsScene = new Scene(exerciseOptionsPane, 400, 600);
        
        // Configure and show primaryStage
        primaryStage.setTitle("Language Trainer");
        primaryStage.setScene(exerciseOptionsScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(UserInterfaceGraphical.class);
    }
}
