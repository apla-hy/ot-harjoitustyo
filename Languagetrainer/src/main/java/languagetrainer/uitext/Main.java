
package languagetrainer.uitext;

import java.util.Scanner;

/**
 * Class that starts the text user interface. This class is not used in the current version of the application.
 */

public class Main {
    
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        UserInterfaceText uiText = new UserInterfaceText(reader);

        uiText.start();
    }
    
    
}
