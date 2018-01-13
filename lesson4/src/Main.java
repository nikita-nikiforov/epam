import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        deleteDoubleLetters();
//        alignToRight();
        burrowsWheeler();
    }

    public static void deleteDoubleLetters(){
        String text = readTextFromConsole();
        String textWithoutDoubleLetters = getTextWithDeletedDoubleLetters(text);
        System.out.println(textWithoutDoubleLetters);
    }

    private static String getTextWithDeletedDoubleLetters(String text){
        Scanner scanner = new Scanner(text);
        StringBuilder resultString = new StringBuilder();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            char[] charArray = line.toCharArray();

            // Temporal string for the current line. Created with the first letter of text
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
        // 80 is a width of the console line , 30 is a width of an aligned column
        String alignedString = getStringAlignedToRight(80, 30, text);
        System.out.println(alignedString);
    }

    private static String getStringAlignedToRight(int consoleLineWidth, int alignedColumnWidth, String text){
        StringBuilder alignedText = new StringBuilder();

        // fixed whitespaces before every line of aligned column
        char[] whitespaces = new char[consoleLineWidth-alignedColumnWidth];
        Arrays.fill(whitespaces, ' ');

        Scanner textScanner = new Scanner(text);

        StringBuilder alignedLine = new StringBuilder();
        while(textScanner.hasNext()){
            String word = textScanner.next();
            if(alignedLine.length()+word.length() < alignedColumnWidth){
                alignedLine.append(word + " ");
            }else{
                char[] alignedLineWhitespaces = new char[alignedColumnWidth-alignedLine.length()];
                Arrays.fill(alignedLineWhitespaces, ' ');
                alignedText.append(whitespaces).append(alignedLineWhitespaces)
                        .append(alignedLine + "\n");
                alignedLine.setLength(0);
                alignedLine.append(word + " ");
            }
        }
        // TODO remove double code
        char[] alignedLineWhitespaces = new char[alignedColumnWidth-alignedLine.length()];
        Arrays.fill(alignedLineWhitespaces, ' ');
        alignedText.append(whitespaces).append(alignedLineWhitespaces).append(alignedLine);

        return alignedText.toString();
    }

    public static void burrowsWheeler(){
        String textToTransform = readTextFromConsole();
        String result = burrowsWheelerTransformation(textToTransform);
        System.out.println(result);
    }

    private static String burrowsWheelerTransformation(String inputText){
        String text = inputText.trim();
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
                if(textRotations[j-1].compareTo(textRotations[j])>0){
                    temp = textRotations[j-1];
                    textRotations[j-1] = textRotations[j];
                    textRotations[j] = temp;
                }
            }
        }

        // Create a string of last characters of all sorted rotation
        StringBuilder lastCharsOfRotations = new StringBuilder();
        for(String textRotation : textRotations){
            lastCharsOfRotations.append(textRotation.substring(text.length()-1));
        }
        return lastCharsOfRotations.toString();
    }

    private static String readTextFromConsole(){
        StringBuilder text = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            text.append(line + "\n");
        }
        return text.toString();
    }
}