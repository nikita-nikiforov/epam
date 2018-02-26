package symbol;

import symbol.exclamationchecks.LineCheck;
import java.util.HashSet;
import java.util.Set;

public class ExclamationAction implements Action {
    // Set of checks on different conditions of the action '!'
    Set<LineCheck> checks = new HashSet();

    @Override
    public void takeAction(String line, StringBuilder scope) {
        boolean checkCompleted = false;
        for(LineCheck check : checks){
            checkCompleted = check.makeAction(line, scope);
            // After taking the action LineCheck returns "true" and the loop can break
            if(checkCompleted) break;
        }
        scope = new StringBuilder();
    }

    public void addCheck(LineCheck lineCheck){
        checks.add(lineCheck);
    }
}