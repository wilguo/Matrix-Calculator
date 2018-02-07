import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Wilbert Guo
 *
 */
public class Calculator {

    /**
     * @param args
     */
    public static void main(String[] args) {

        //create new Scanner for User Input
        Scanner sc = new Scanner(System.in);

        //Get user input for matrix # 1
        System.out.println("Matrix #1");
        System.out.print("Enter number of rows: ");
        int numRows1 = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int numColumns1 = sc.nextInt();
        sc.nextLine();

        //create 2-dimensional array for matrix #1
        int[][] matrix1 = new int[numRows1][numColumns1];

        //Populate matrix1 with user input
        for(int a = 0; a < numRows1; a++){
            String input = sc.nextLine();
            //separate each line by spaces and store them in temp array named line
            String[] line = input.split("\\s");

            //transfer elements in temp array to matrix array and converting each
            //string element in temp to int to be stored in matrix array
            for(int b = 0; b < line.length; b++){
                matrix1[a][b] = Integer.parseInt(line[b]);
            }
        }

        //ask user input for the operation they would like to perform
        System.out.print("Enter operation (+/-/*): ");
        String operation = sc.nextLine();

        //user input for matrix #2
        System.out.println("Matrix #2");
        System.out.print("Enter number of rows: ");
        int numRows2 = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int numColumns2 = sc.nextInt();
        sc.nextLine();

        //create 2-dimensional array for matrix #2
        int[][] matrix2 = new int[numRows2][numColumns2];

        //populate matrix2 with user input (same as matrix 1)
        for(int a = 0; a < numRows2; a++){
            String input = sc.nextLine();
            String[] line = input.split("\\s");

            for(int b = 0; b < line.length; b++){
                matrix2[a][b] = Integer.parseInt(line[b]);
            }
        }

        //if operation equals +, then perform matrix addition
        if(operation.equals("+")){
            //check to see if matrix demensions are the same in order to perform addition
            if((numRows1 == numRows2) && (numColumns1 == numColumns2)){
                //use add method to add two matrices together
                //store the answer in a new 2-dimensional matrix named result
                int [][] result = add(numRows1, numColumns1, numRows2, numColumns2,
                        matrix1, matrix2);

                System.out.println("Your result is: ");

                //print out result
                for(int i = 0; i < result.length; i++){
                    for(int j = 0; j < result[i].length; j++){
                        System.out.print(result[i][j] + "    ");
                    }
                    System.out.println();
                    System.out.println();
                }
            }else{
                //if the two matrices do not have the same dimensions them matrix addition
                //is not defined and prints out error message to user
                System.out.println("Invalid operation for matrix addition since matrix "
                        + "dimensions are not the same.");
            }

        }else if(operation.equals("-")){
            //checks to see if two matrices have same dimensions in order to perform matrix
            //subtraction
            if((numRows1 == numRows2) && (numColumns1 == numColumns2)){
                //use the subtract method to subtract 2 matrices
                //store the answer in the result array
                int [][] result = subtract(numRows1, numColumns1, numRows2, numColumns2,
                        matrix1, matrix2);

                System.out.println("Your result is: ");

                //print out result
                for(int i = 0; i < result.length; i++){
                    for(int j = 0; j < result[i].length; j++){
                        System.out.print(result[i][j] + "    ");
                    }
                    System.out.println();
                    System.out.println();
                }
            }else{
                //print out error message if 2 matrices are not legal for matrix subtraction
                System.out.println("Invalid operation for matrix subtraction since matrix "
                        + "dimensions are not the same.");
            }

        }else if(operation.equals("*")){
            //check to see if two matrix can be multiplied
            if(numColumns1 == numRows2){
                //use the multiply method to multiply 2 matrices
                //store the answer into an array named result
                int [][] result = multiply(numRows1, numColumns1, numRows2, numColumns2,
                        matrix1, matrix2);

                System.out.println("Your result is: ");

                //print out the result of 2 matrices being multiplied together
                for(int i = 0; i < result.length; i++){
                    for(int j = 0; j < result[i].length; j++){
                        System.out.print(result[i][j] + "    ");
                    }
                    System.out.println();
                    System.out.println();
                }
            }else{
                //print out error message to user if the two matrices do not follow
                //the specified dimensions for matrix multiplication
                System.out.println("Invalid operation for matrix multiplication since number"
                        + "of columns in matrix 1 is not equal to number of rows in matrix 2.");
            }
        }else{
            //print out message for user to enter a valid operation specified in the instructions
            //if not operand is not recognized
            System.out.println("Please enter a valid operation.");
        }


    }

    public static int[][] add(int row1, int column1, int row2, int column2,
                              int[][] matrix1, int[][] matrix2){

        //create new array to store answer
        int[][] arr = new int[row1][column1];

        //add corresponding entries in each matrix
        for(int i = 0; i < row1; i++){
            for(int j = 0; j < column1; j++){
                arr[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return arr;
    }

    public static int[][] subtract(int row1, int column1, int row2, int column2,
                                   int[][] matrix1, int[][] matrix2){

        //create new array to store answer
        int[][] arr = new int[row1][column1];

        //add corresponding entries in each matrix
        for(int i = 0; i < row1; i++){
            for(int j = 0; j < column1; j++){
                arr[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }

        return arr;
    }

    public static int[][] multiply(int row1, int column1, int row2, int column2,
                                   int[][] matrix1, int[][] matrix2){

        //create new array to store result of the 2 matrices being multiplied
        int[][] arr = new int[row1][column2];

        //make a copy of matrix 1 and transpose matrix 2 for multiplication later
        int[][] m1 = matrix1;
        int[][] m2 = transpose(row2, column2,matrix2);

        //populate each entry of the answer matrix by matrix multiplication forumla
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                arr[i][j] = dotProduct(m1[i], m2[j]);
            }
        }
        return arr;
    }


    public static int dotProduct(int[] vector1, int[] vector2){
        int sum = 0;

        //calculate the dot product between 2 vectors
        for(int i = 0; i < vector1.length; i++){
            int result = vector1[i] * vector2[i];
            sum += result;
        }

        return sum;
    }

    public static int[][] transpose(int row, int column, int[][] matrix){
        int[][] result = new int[column][row];

        //transpose a matrix and populate the result array entry individually
        for(int i = 0; i < column; i++){
            for(int j = 0; j < row; j++){
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }
}