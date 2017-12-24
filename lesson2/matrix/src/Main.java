import java.util.Random;

public class Main {
    public static Integer matrix[][] = new Integer[4][4];

    public static void buildMatrix(){
        Random randomGenerator = new Random();

        // Fill the matrix under the main diagonal with values from 1 to 10
        for(int i=0, k=1; i<4; i++, k++){
            for(int j=k; j<4; j++){
                matrix[i][j] = randomGenerator.nextInt(9) + 1;
            }
        }

        // Fill the matrix behind the main diagonal with values from 10 to 19
        for(int i=1, k=1; i<4; i++, k++){
            for(int j=0; j<k; j++){
                matrix[i][j] = randomGenerator.nextInt(9) + 10;
            }
        }

        // Fill the main diagonal of matrix with zeros
        for(int i=0, j=0; i<4; i++, j++){
            matrix[i][j] = 0;
        }
    }

    public static void showMatrix(){
        System.out.println("The matrix:");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void countSum(){
        Integer sumUnderDiagonal = 0;
        for(int i=0, k=1; i<4; i++, k++){
            for(int j=k; j<4; j++){
                sumUnderDiagonal += matrix[i][j];
            }
        }

        Integer sumBehindDiagonal = 0;
        for(int i=1, k=1; i<4; i++, k++){
            for(int j=0; j<k; j++){
                sumBehindDiagonal += matrix[i][j];
            }
        }

        System.out.println("Sum of numbers under the main diagonal is " + sumUnderDiagonal);
        System.out.println("Sum of numbers behind the main diagonal is " + sumBehindDiagonal);
    }

    public static void main(String[] args) {
        buildMatrix();
        showMatrix();
        countSum();
    }
}
