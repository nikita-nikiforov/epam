package symbol.exclamationchecks;

public interface LineCheck {
    boolean makeAction(String line, StringBuilder scope);
}