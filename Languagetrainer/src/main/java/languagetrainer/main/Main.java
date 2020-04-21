/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package languagetrainer.main;

/**
 *
 * @author lauri
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner reader = new Scanner(System.in);
        System.out.println("Valitse käynnistettävä käyttöliittymä:\n1: tekstikäyttöliittymä\n2: graafinen käyttöliittymä (toteutus kesken)");
        String row = reader.nextLine();
        
        if (row.equals("1")) {
            // Start the text user interface
            languagetrainer.uitext.Main.main(args);
        } else if (row.equals("2")) {
            // Start the graphical user interface
            languagetrainer.ui.UserInterfaceGraphical.main(args);
        }
    }
}
