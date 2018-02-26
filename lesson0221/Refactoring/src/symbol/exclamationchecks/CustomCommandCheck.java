package symbol.exclamationchecks;

public class CustomCommandCheck implements LineCheck{

    @Override
    public boolean makeAction(String line, StringBuilder scope) {
        if (line.startsWith("!custom_command")){
            runCustomCommand(line, scope);
            return true;
        } else return false;
    }

    private void runCustomCommand(String line, StringBuilder scope){
        // some stuff
    }
}
