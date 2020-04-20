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

import javafx.application.Application;
import javafx.stage.Stage;

public class UserInterfaceGraphical extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Language Trainer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(UserInterfaceGraphical.class);
    }
}
