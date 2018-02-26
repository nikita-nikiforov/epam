package symbol.exclamationchecks;

public class ExecuteCheck implements LineCheck {

    @Override
    public boolean makeAction(String line, StringBuilder scope) {
        if (line == "!execute"){
            executeScope(scope);
            return true;
        } else return false;
    }

    private void executeScope(StringBuilder scope){
        // some stuff
    }
}