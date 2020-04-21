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
import javafx.scene.control.TextField;
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
        ArrayList<ExerciseOrder> orders = new ArrayList<>();
        orders.add(ExerciseOrder.ASCENDING);
        orders.add(ExerciseOrder.DESCENDING);
        orders.add(ExerciseOrder.RANDOM);
        
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
        // Word tenses
        ArrayList<String> options4 = new ArrayList<>();
        for (WordTense tense: tenses) {
            options4.add(translations.getString(tense.toString()));
        }
        // Exercise orders
        ArrayList<String> options6 = new ArrayList<>();
        for (ExerciseOrder order: orders) {
            options6.add(translations.getString(order.toString()));
        }
                
        // Exercise options scene
        
        // Create components
        BorderPane exerciseOptionsPane = new BorderPane();
        VBox centerPane = new VBox(12);
        HBox bottomPane = new HBox(12);
        centerPane.setSpacing(10);
        
        // Main text
        Label mainText = new Label("Valitse asetukset uudelle harjoitukselle.");
        
        // Question language
        Label optionsText1 = new Label("Kysymyksien kieli");
        ChoiceBox<String> choiceQuestionLanguage = new ChoiceBox<String>(FXCollections.observableArrayList(options1));
        choiceQuestionLanguage.setValue(options1.get(0));
        
        // Answer language
        Label optionsText2 = new Label("Vastauksien kieli");
        ChoiceBox<String> choiceAnswerLanguage = new ChoiceBox<String>(FXCollections.observableArrayList(options2));
        choiceAnswerLanguage.setValue(options2.get(0));
        
        // Word types
        Label optionsText3 = new Label("Sanaluokat");
        ArrayList<CheckBox> typeBoxes = new ArrayList<>();
        for (String option: options3) {
            CheckBox cb = new CheckBox(option);
            typeBoxes.add(cb);
        }
        typeBoxes.get(0).setSelected(true);
        
        // Verb tenses
        Label optionsText4 = new Label("Aikamuodot");
        ArrayList<CheckBox> tenseBoxes = new ArrayList<>();
        for (String option: options4) {
            CheckBox cb = new CheckBox(option);
            tenseBoxes.add(cb);
        }
        tenseBoxes.get(0).setSelected(true);
        
        // Number of tasks
        Label optionsText5 = new Label("Tehtävien määrä");
        TextField tfNumberOfTasks = new TextField();
        tfNumberOfTasks.setText("1");
        
        // Order of tasks
        Label optionsText6 = new Label("Tehtävien järjestys");
        ChoiceBox<String> choiceExerciseOrder = new ChoiceBox<String>(FXCollections.observableArrayList(options6));
        choiceExerciseOrder.setValue(options6.get(0));
        
        // Status label
        Label statusText = new Label("");
        
        // Button start new exercise
        Button startButton = new Button("Aloita uusi harjoitus");
        startButton.setOnAction((event) -> {
            String selection = choiceQuestionLanguage.getValue();
            Language selectedQuestionLanguage = questionLanguages.get(options1.indexOf(selection));
            selection = choiceAnswerLanguage.getValue();
            Language selectedAnswerLanguage = answerLanguages.get(options2.indexOf(selection));
            ArrayList<WordType> selectedTypes = new ArrayList<>();
            for (int i = 0; i < typeBoxes.size(); i++) {
                if (typeBoxes.get(i).selectedProperty().getValue()) {
                    selectedTypes.add(types.get(i));
                }
            }
            ArrayList<WordTense> selectedTenses = new ArrayList<>();
            for (int i = 0; i < tenseBoxes.size(); i++) {
                if (tenseBoxes.get(i).selectedProperty().getValue()) {
                    selectedTenses.add(tenses.get(i));
                }
            }
            String selectedNumberOfTasks = tfNumberOfTasks.getText();
            selection = choiceExerciseOrder.getValue();
            ExerciseOrder selectedOrder = orders.get(options6.indexOf(selection));
            statusText.setText(selectedQuestionLanguage.toString() + " " + selectedAnswerLanguage.toString() + " " + selectedTypes + " " + selectedTenses + " " + selectedNumberOfTasks+ " " + selectedOrder);
            this.startExercise(tasks, selectedQuestionLanguage, selectedAnswerLanguage, selectedTypes, selectedTenses, Integer.valueOf(selectedNumberOfTasks), selectedOrder);
        });
        
        // Add components to center pane
        centerPane.getChildren().addAll(optionsText1, choiceQuestionLanguage, optionsText2, choiceAnswerLanguage, optionsText3);
        for (CheckBox cb: typeBoxes) {
            centerPane.getChildren().add(cb);
        }
        centerPane.getChildren().add(optionsText4);
        for (CheckBox cb: tenseBoxes) {
            centerPane.getChildren().add(cb);
        }
        centerPane.getChildren().addAll(optionsText5, tfNumberOfTasks, optionsText6, choiceExerciseOrder);
        
        // Add components to bottom pane
        bottomPane.getChildren().addAll(startButton, statusText);
        
        // Add components to excercise options main pane
        exerciseOptionsPane.setTop(mainText);
        exerciseOptionsPane.setCenter(centerPane);
        exerciseOptionsPane.setBottom(bottomPane);
        exerciseOptionsScene = new Scene(exerciseOptionsPane, 800, 600);
        
        // Configure and show primaryStage
        primaryStage.setTitle("Language Trainer");
        primaryStage.setScene(exerciseOptionsScene);
        primaryStage.show();
    }
    
    public void startExercise(ArrayList<Task> tasks, Language questionLanguage, Language answerLanguage, ArrayList<WordType> types, ArrayList<WordTense> tenses, int numberOfTasks, ExerciseOrder order) {
        System.out.println("Aloitus");
    }

    public static void main(String[] args) {
        launch(UserInterfaceGraphical.class);
    }
}
