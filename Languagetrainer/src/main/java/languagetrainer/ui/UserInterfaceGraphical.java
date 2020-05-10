
package languagetrainer.ui;

import languagetrainer.domain.*;
import java.util.ArrayList;

import java.util.Locale;
import java.util.ResourceBundle;

import java.util.Properties;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;

/**
 * Class for the graphical user interface
 */

public class UserInterfaceGraphical extends Application {
    
    private Scene exerciseOptionsScene;
    private Scene exerciseScene;
    private TaskList taskList;
    private Exercise exercise;
    private Task currentTask;
    private Label questionText;
    
    @Override
    public void start(Stage primaryStage) {
        
        // Create task list
        String dataFile = "";
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config.properties"));
            dataFile = properties.getProperty("dataFile");
        } catch (Exception e) {
            System.out.println("Tiedoston config.properties lukeminen ei onnistunut");
            System.exit(0);
        }
        try {
            this.taskList = new TaskList(dataFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
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
        // Start characters ie. all characters from a to z
        ArrayList<String> options7 = new ArrayList<>();
        for (int i = 97; i < 123; i++) {
            options7.add(Character.toString ((char) i));
        }
    
        // *** Exercise options scene ***
        
        // Create components
        BorderPane optionsPane = new BorderPane();
        GridPane topPaneOptions = new GridPane();
        VBox centerPaneOptions = new VBox(12);
        HBox bottomPaneOptions = new HBox(2);
        centerPaneOptions.setSpacing(10);
        
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
        tfNumberOfTasks.setText("5");
        
        // Order of tasks
        Label optionsText6 = new Label("Tehtävien järjestys");
        ChoiceBox<String> choiceExerciseOrder = new ChoiceBox<String>(FXCollections.observableArrayList(options6));
        choiceExerciseOrder.setValue(options6.get(0));
        
        // Start character
        Label optionsText7 = new Label("Aloituskirjain");
        ChoiceBox<String> choiceExerciseStartChar = new ChoiceBox<String>(FXCollections.observableArrayList(options7));
        choiceExerciseStartChar.setValue(options7.get(0));

        // Label for status messages
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
            selection = choiceExerciseOrder.getValue();
            ExerciseOrder selectedOrder = orders.get(options6.indexOf(selection));
            selection = tfNumberOfTasks.getText();
            if (this.isInteger(selection)) {
                int selectedNumberOfTasks = Integer.valueOf(selection);
                boolean result = this.startExercise(tasks, selectedQuestionLanguage, selectedAnswerLanguage, selectedTypes, selectedTenses, selectedNumberOfTasks, selectedOrder, choiceExerciseStartChar.getValue());
                if (result) {
                    this.currentTask = this.exercise.getNextTask();
                    if (this.currentTask != null) {
                        statusText.setText("");
                        this.setQuestionText();
                        primaryStage.setScene(exerciseScene);
                    } else {
                        statusText.setText("Valinnoilla ei löydy yhtään tehtävää.");
                    }
                }
            } else {
                statusText.setText("Virheellinen luku");
            }
        });
        
        // Button exit
        Button exitButton = new Button("Lopeta");
        exitButton.setOnAction((event) -> {
            System.exit(0);
        });
        
        // Add components to center pane
        centerPaneOptions.getChildren().addAll(mainText, optionsText1, choiceQuestionLanguage, optionsText2, choiceAnswerLanguage, optionsText3);
        for (CheckBox cb: typeBoxes) {
            centerPaneOptions.getChildren().add(cb);
        }
        centerPaneOptions.getChildren().add(optionsText4);
        for (CheckBox cb: tenseBoxes) {
            centerPaneOptions.getChildren().add(cb);
        }
        centerPaneOptions.getChildren().addAll(optionsText5, tfNumberOfTasks, optionsText6, choiceExerciseOrder, optionsText7, choiceExerciseStartChar);
        
        
        // Add components to bottom pane
        bottomPaneOptions.getChildren().addAll(startButton, statusText);
        
        // Add components to excercise options main pane
        optionsPane.setTop(topPaneOptions);
        optionsPane.setRight(exitButton);
        optionsPane.setCenter(centerPaneOptions);
        optionsPane.setBottom(bottomPaneOptions);
        exerciseOptionsScene = new Scene(optionsPane, 800, 600);
                
        // *** Exercise scene ***
        
        // Create components
        BorderPane exercisePane = new BorderPane();
        VBox centerPaneExercise = new VBox();
        GridPane centerGridPaneExercise = new GridPane();
        HBox bottomPaneExercise = new HBox(2);
        
        // Question text
        questionText = new Label("");
        
        // Answer labels
        ArrayList<Label> answerLabels = new ArrayList<>();
        String[] answerTexts = {"Perusmuoto", "Minä", "Sinä", "Hän", "Me", "Te", "He" };
        for (String text: answerTexts) {
            Label lb = new Label(text);
            answerLabels.add(lb);
        }
        
        // Answer text boxes
        ArrayList<TextField> answers = new ArrayList<>();
        for (String text: answerTexts) {
            TextField tf = new TextField("");
            answers.add(tf);
        }
        
        // Result labels
        ArrayList<Label> resultLabels = new ArrayList<>();
        for (String text: answerTexts) {
            Label lb = new Label("");
            resultLabels.add(lb);
        }
        
        // Notes label and text box
        Label notesLabel = new Label("Muistiinpanot ja esimerkit:");
        TextField notesTextField = new TextField("");
        
        // Label for status messages
        Label statusTextExercise = new Label("");

        // Button show answers
        Button showAnswersButton = new Button("Näytä vastaukset");
        showAnswersButton.setOnAction((event) -> {
            if (this.currentTask != null) {
                for (int i = 0; i < answers.size(); i++) {
                    resultLabels.get(i).setText(this.currentTask.getAnswer().get(i));
                }
                notesTextField.setText(this.currentTask.getNotes());
                statusTextExercise.setText("");
            }
        });
        
        // Button next task
        Button nextTaskButton = new Button("Seuraava tehtävä");
        nextTaskButton.setOnAction((event) -> {
            for (int i = 0; i < answers.size(); i++) {
                resultLabels.get(i).setText("");
            }
            notesTextField.setText("");
            statusTextExercise.setText("");
            this.currentTask = this.exercise.getNextTask();
            for (int i = 0; i < answers.size(); i++) {
                answers.get(i).setText("");
            }
            if (this.currentTask != null) {
                this.setQuestionText();
            } else {
                this.questionText.setText("Olet vastannut kaikkiin kysymyksiin. Palaa päävalikkoon painamalla Lopeta harjoitus -nappia.");
            }
        });
        
        // Button stop
        Button stopButton = new Button("Lopeta harjoitus");
        stopButton.setOnAction((event) -> {
            for (int i = 0; i < answers.size(); i++) {
                answers.get(i).setText("");
                resultLabels.get(i).setText("");
            }
            notesTextField.setText("");
            statusTextExercise.setText("");
            primaryStage.setScene(exerciseOptionsScene);
        });
                
        // Button save notes
        Button saveNotesButton = new Button("Tallenna muistiinpanot");
        saveNotesButton.setOnAction((event) -> {
            if (this.currentTask != null) {
                String notes = notesTextField.getText();
                // Check that notes don't contain ";" since it is the delimiter in the file format
                if (notes.contains(";")) {
                    statusTextExercise.setText("Muistiinpanoissa ei saa olla puolipistettä");
                } else {
                    this.currentTask.setNotes(notes);
                    taskList.save();
                    statusTextExercise.setText("Tallennettu");
                }
            } else {
                statusTextExercise.setText("Tallennus ei onnistunut");
            }
        });
                
        // Add components to center grid pane
        for (int i = 0; i < answerLabels.size(); i++) {
            centerGridPaneExercise.add(answerLabels.get(i), 1, i+1);
        }
        for (int i = 0; i < answers.size(); i++) {
            centerGridPaneExercise.add(answers.get(i), 2, i+1);
        }
        for (int i = 0; i < resultLabels.size(); i++) {
            centerGridPaneExercise.add(resultLabels.get(i), 3, i+1);
        }
        
        // add components to center pane
        centerPaneExercise.getChildren().addAll(questionText, centerGridPaneExercise, notesLabel, notesTextField, saveNotesButton);
        
        // Add components to bottom pane
        bottomPaneExercise.getChildren().addAll(showAnswersButton, nextTaskButton, statusTextExercise);
        
        // Add components to excercise main pane
        exercisePane.setRight(stopButton);
        exercisePane.setCenter(centerPaneExercise);
        exercisePane.setBottom(bottomPaneExercise);
        exerciseScene = new Scene(exercisePane, 800, 600);
        
        // Configure and show primaryStage
        primaryStage.setTitle("Language Trainer");
        primaryStage.setScene(exerciseOptionsScene);
        primaryStage.show();
        
    }
    
    /**
     * The method checks if the given string can be converted to an integer
     * @param number the string to be checked
     * 
     * @return true if the string can be converted to integer, false if not
     */
    private boolean isInteger(String number) {
        try {
            Integer.valueOf(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * The method updates the question text of the current task to the user interface
     */
    private void setQuestionText() {
        String currentQuestion = this.currentTask.getQuestion();
        this.questionText.setText("Mitä on " + currentQuestion + " espanjaksi?");
    }
    
    /**
     * The method creates a new Exercise and assigns it to the class variable exercise
     */
    private boolean startExercise(ArrayList<Task> tasks, Language selectedQuestionLanguage, Language selectedAnswerLanguage, ArrayList<WordType> selectedTypes, ArrayList<WordTense> selectedTenses, int selectedNumberOfTasks, ExerciseOrder selectedOrder, String startChar) {
        
        this.exercise = new Exercise(tasks, selectedQuestionLanguage, selectedAnswerLanguage, selectedTypes, selectedTenses, selectedNumberOfTasks, selectedOrder, startChar);
        if (this.exercise != null) {
            return true;
        }
        return false;
    }

    /**
     * The methods starts the graphical user interface.
     */
    
    public static void main(String[] args) {
        launch(UserInterfaceGraphical.class);
    }
}
