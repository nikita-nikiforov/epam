package symbol;

public class DollarAction implements Action {

    @Override
    public void takeAction(String line, StringBuilder scope) {
        addToVariable(line);
    }

    private void addToVariable(String line){
        // some stuff
    }
}