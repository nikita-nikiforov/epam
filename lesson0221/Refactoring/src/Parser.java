import symbol.Action;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Parser{
    private Map<Character, Action> characters = new HashMap<>();

    public void parse(String text){
        Scanner scanner = new Scanner(text);
        StringBuilder scope = new StringBuilder();
        String line = scanner.nextLine();
        while (line != null)
        {
            // Get action from HashMap and take it
            Action action = characters.get(line.charAt(0));
            action.takeAction(line, scope);

            line = scanner.nextLine();
        }
    }

    public void addCharacter(char character, Action symbol){
        characters.put(character, symbol);
    }
}