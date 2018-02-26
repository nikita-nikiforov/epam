package symbol.exclamationchecks;

public class SingleLineDirectiveCheck implements LineCheck {

    @Override
    public boolean makeAction(String line, StringBuilder scope) {
        if (line == "!single_line_directive"){
            processDirective(line);
            return true;
        } else return false;
    }

    private void processDirective(String line){
        // some stuff
    }
}