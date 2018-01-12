import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        deleteDoubleLetters();
//        alignToRight();
        burrowsWheeler();
    }

    private static void deleteDoubleLetters(){
        String resultString = "";
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            char[] charArray = line.toCharArray();
            String resultLine = String.valueOf(charArray[0]);
            for(int i=1; i<charArray.length; i++){
                if(charArray[i]!=resultLine.charAt(resultLine.length()-1)){
                    resultLine += charArray[i];
                }else{
                    resultLine+= "___";
                }
            }
            resultString += resultLine + "\n";
        }
        System.out.println(resultString);
    }

    public static void alignToRight(){
        String text = readTextFromConsole();
        String alignedString = getStringAlignedToRight(80, 30, text);
        System.out.println(alignedString);
    }

    private static String getStringAlignedToRight(int consoleLineWidth, int alignedTextWidth, String text){
        StringBuilder alignedText = new StringBuilder();
        char[] whitespaces = new char[consoleLineWidth-alignedTextWidth];
        Arrays.fill(whitespaces, ' ');

        Scanner textScanner = new Scanner(text);

        StringBuilder alignedLine = new StringBuilder();
        while(textScanner.hasNext()){
            String word = textScanner.next();
            if(alignedLine.length()+word.length() < alignedTextWidth){
                alignedLine.append(word + " ");
            }else{
                char[] alignedLineWhitespaces = new char[alignedTextWidth-alignedLine.length()];
                Arrays.fill(alignedLineWhitespaces, ' ');
                alignedText.append(whitespaces).append(alignedLineWhitespaces)
                        .append(alignedLine + "\n");
                alignedLine.setLength(0);
                alignedLine.append(word + " ");
            }
        }
        char[] alignedLineWhitespaces = new char[alignedTextWidth-alignedLine.length()];
        Arrays.fill(alignedLineWhitespaces, ' ');
        alignedText.append(whitespaces).append(alignedLineWhitespaces).append(alignedLine);

        return alignedText.toString();
    }

    private static String readTextFromConsole(){
        StringBuilder text = new StringBuilder("");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            text.append(line + " ");
        }
        return text.toString();
    }

    public static void burrowsWheeler(){
        String textToTransform = readTextFromConsole();
        String result = burrowsWheelerTransformation(textToTransform);
        System.out.println(result);
    }

    private static String burrowsWheelerTransformation(String inputText){
        String text = inputText.trim();
        String[] textRotations = new String[text.length()]; // All possible rotations of text
        textRotations[0] = text;
        for(int i = 1; i<text.length(); i++){
            String newRotation = textRotations[i-1].substring(text.length()-1)
                    + textRotations[i-1].substring(0, text.length()-1);
            textRotations[i] = newRotation;
        }

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

        StringBuilder resultString = new StringBuilder();

        for(String textRotation : textRotations){
            resultString.append(textRotation.substring(text.length()-1));
        }
        return resultString.toString();
    }
}