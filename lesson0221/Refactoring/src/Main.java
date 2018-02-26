import symbol.DollarAction;
import symbol.ExclamationAction;
import symbol.exclamationchecks.CustomCommandCheck;
import symbol.exclamationchecks.ExecuteCheck;
import symbol.exclamationchecks.SingleLineDirectiveCheck;

public class Main {

    public static void main(String[] args) {
        ExclamationAction exclamationAction = new ExclamationAction();
        exclamationAction.addCheck(new CustomCommandCheck());
        exclamationAction.addCheck(new ExecuteCheck());
        exclamationAction.addCheck(new SingleLineDirectiveCheck());

        Parser parser = new Parser();
        parser.addCharacter('$', new DollarAction());
        parser.addCharacter('!', exclamationAction);
    }
}