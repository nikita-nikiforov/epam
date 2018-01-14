import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("Hello! Choose a program, please:\n1. Delete double letters" +
                "\n2. Align text to right\n3. Burrows-Wheller transformation\nEnter: ");
        Scanner scanner = new Scanner(System.in);
        int choise = scanner.nextInt();
        switch(choise){
            case 1:
                deleteDoubleLetters();
                break;
            case 2:
                alignToRight();
                break;
            case 3:
                burrowsWheeler();
                break;
            default:
                System.out.println("Wrong selection.");;
        }
//        deleteDoubleLetters();
//        alignToRight();
//        burrowsWheeler();
    }

    public static void deleteDoubleLetters(){
        String text = readTextFromConsole();
        if(text.isEmpty()){
            System.out.println("Firstly, enter the text");
            return;
        }
        String textWithoutDoubleLetters = getTextWithDeletedDoubleLetters(text);
        System.out.println(textWithoutDoubleLetters);
    }

    private static String getTextWithDeletedDoubleLetters(String text){
        Scanner scanner = new Scanner(text);
        StringBuilder resultString = new StringBuilder();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            char[] charArray = line.toCharArray();

            // Temporal string for the current line. Created with the first letter of the text
            StringBuilder resultLine = new StringBuilder(String.valueOf(charArray[0]));
            for(int i=1; i<charArray.length; i++){
                if(charArray[i]!=resultLine.charAt(resultLine.length()-1)){   // check for doubles
                    resultLine.append(charArray[i]);
                }
            }
            resultString.append(resultLine + "\n");
        }
        return resultString.toString();
    }

    public static void alignToRight(){
        String text = readTextFromConsole();
        if(text.isEmpty()){
            System.out.println("There is nothing to align. Enter the text.");
            return;
        }
        // 80 is a width of the console line, 30 is a width of an aligned column
        String alignedString = getStringAlignedToRight(80, 30, text);
        System.out.println(alignedString);
    }

    private static String getStringAlignedToRight(int consoleLineWidth, int alignedColumnWidth,
                                                  String text){
        StringBuilder alignedText = new StringBuilder();

        // fixed whitespaces before every line of aligned column
        char[] whitespaces = new char[consoleLineWidth-alignedColumnWidth];
        Arrays.fill(whitespaces, ' ');

        Scanner lineScanner = new Scanner(text);        // Scanner of lines of the text

        StringBuilder alignedLine = new StringBuilder();
        while(lineScanner.hasNext()){
            String line = lineScanner.nextLine();
            Scanner wordScanner = new Scanner(line);     // Scanner of words of a line

            while(wordScanner.hasNext()){
                String word = wordScanner.next();

                // if the next added word isn't exceed the width of the aligned column
                // and the word isn't the last in a line, add it and continue the while loop
                if(alignedLine.length()+word.length() < alignedColumnWidth){
                    alignedLine.append(word + " ");
                    if(wordScanner.hasNext()) continue;
                }

                // otherwise, if the word exceeds the width of the aligned column or is the last
                // in a line, append whitespaces to alignedLine
                char[] alignedLineWhitespaces = new char[alignedColumnWidth-alignedLine.length()];
                Arrays.fill(alignedLineWhitespaces, ' ');
                // append alignedLine to the whole result text
                alignedText.append(whitespaces).append(alignedLineWhitespaces)
                        .append(alignedLine + "\n");

                alignedLine.setLength(0);                 // reset alignedLine
                // leave loop if it's the last word (we already have appended the word)
                if(!wordScanner.hasNext()) continue;
                alignedLine.append(word + " ");           // and add the word to a new line
            }
            alignedText.append("\n");
        }
        return alignedText.toString();
    }

    public static void burrowsWheeler(){
        String textToTransform = readTextFromConsole();
        if(textToTransform.isEmpty()){
            System.out.println("There's nothing to transform. Firstly, enter the text.");
            return;
        }
        String result = burrowsWheelerTransformation(textToTransform);
        System.out.println(result);
    }

    private static String burrowsWheelerTransformation(String inputText){
        String text = inputText.trim().toUpperCase();
        // replace all characters with dots except letters and digits
        text = text.replaceAll("[^a-zA-Z0-9]", ".");
        String[] textRotations = new String[text.length()]; //Array for all possible rotations of text
        textRotations[0] = text;

        // Replace the last letter of the previous rotation
        // to the beginning of current (ie. JAVA -> AJAV -> VAJA)
        for(int i = 1; i<text.length(); i++){
            String newRotation = textRotations[i-1].substring(text.length()-1)
                    + textRotations[i-1].substring(0, text.length()-1);
            textRotations[i] = newRotation;
        }

        // Bubble sort of text rotations
        String temp;
        for(int i=0; i<textRotations.length; i++){
            for(int j=1; j<textRotations.length-i; j++){
                if(textRotations[j-1].compareTo(textRotations[j]) > 0){
                    temp = textRotations[j-1];
                    textRotations[j-1] = textRotations[j];
                    textRotations[j] = temp;
                }
            }
        }

        // Iterate through all text rotations; create a string of last
        // characters of all sorted rotation
        StringBuilder lastCharsOfRotations = new StringBuilder();
        for(String textRotation : textRotations){
            lastCharsOfRotations.append(textRotation.substring(text.length()-1));
        }
        return lastCharsOfRotations.toString();
    }

    private static String readTextFromConsole(){
        StringBuilder text = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the text, press Enter and then Ctrl+D: ");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            text.append(line + "\n");
        }
        return text.toString();
    }
}