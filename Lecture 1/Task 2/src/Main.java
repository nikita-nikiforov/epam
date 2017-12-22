import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static int[] array;

    //Get numbers from scanner
    public static void inputNumbers(int amountOfNumbers){
        Scanner scanner = new Scanner(System.in);
        array = new int[amountOfNumbers];
        System.out.println("Enter " + amountOfNumbers + " numbers:");
        for(int i=0;i<amountOfNumbers;i++){
            array[i] = scanner.nextInt();
        }
    }

    public static void makeBubbleSort(int arr[]){
        int sortedArr[] = Arrays.copyOf(arr,arr.length);
        bubbleSortArray(sortedArr);
        System.out.println("Sorted array:");
        printArray(sortedArr);
        System.out.println();
    }

    public static int[] bubbleSortArray(int arr[]){
        int temp;
        for(int i=0; i<arr.length; i++){
            for(int j=1;j<arr.length-i;j++){
                if(arr[j-1]>arr[j]){
                    temp = arr[j-1];
                    arr[j-1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        return arr;
    }

    public static void printWhereDigitsInAscendingOrder(int arr[]){
        int[] ascendingOrderArray = getArrayWithDigitsInAscendingOrder(arr);
        System.out.println("Numbers with digits in ascending order:");
        printArray(ascendingOrderArray);
        System.out.println();
    }

    public static int[] getArrayWithDigitsInAscendingOrder(int[] arr){
        int[] ascendingOrderArray = new int[arr.length];
        for(int i=0, indexOfAscendOrderArray=0;i<arr.length;i++){
            if (digitsInAscendingOrder(arr[i])){
                ascendingOrderArray[indexOfAscendOrderArray] = arr[i];
                indexOfAscendOrderArray++;
            }
        }
        return ascendingOrderArray;
    }

    // Checks whether a number has digits in ascending order. Checks from the end.
    public static boolean digitsInAscendingOrder(int number) {
        if(number%10==0) return false;
        int previousDigit = 0; //last digit in a checking number.
        while (number>0){
            int currentDigit = number%10;
            if(previousDigit!=0 && (currentDigit>=previousDigit || currentDigit==0)) return false;
            number/=10;
            previousDigit = currentDigit;
        }
        return true;
    }

    public static void printArrayOfNumbersDivisibleByPrevious(int arr[]){
        int[] newArray = getArrayOfNumbersDivisibleByPrevious(arr);
        System.out.println("Numbers divisible by previous:");
        printArray(newArray);
        System.out.println();
    }

    public static int[] getArrayOfNumbersDivisibleByPrevious(int arr[]){
        int[] newArray = new int[arr.length];
        for(int i=1, newArrayIndex=0;i<arr.length;i++){
            if(arr[i-1]!=0 && arr[i]%arr[i-1]==0){
                newArray[newArrayIndex] = arr[i];
                newArrayIndex++;
            }
        }
        return newArray;
    }

    public static void printPrimeNumbers(int[] arr){
        int[] newArray = getArrayOfPrimeNumbers(arr);
        System.out.println("Prime numbers:");
        printArray(newArray);
    }

    public static int[] getArrayOfPrimeNumbers(int[] arr){
        int[] newArray = new int[arr.length];
        for(int i=0, newArrayIndex=0;i<arr.length;i++){
            if(isPrime(arr[i])){
                newArray[newArrayIndex]=arr[i];
                newArrayIndex++;
            }
        }
        return newArray;
    }

    public static boolean isPrime(int number){
        if(number%2==0){
            return false;
        }
        for(int i=3; i*i<=number;i+=2){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr){
        for(int i=0;i< arr.length;i++){
            if(arr[i]!=0) System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        inputNumbers(5);
        makeBubbleSort(array);
        printWhereDigitsInAscendingOrder(array);
        printArrayOfNumbersDivisibleByPrevious(array);
        printPrimeNumbers(array);
    }
}