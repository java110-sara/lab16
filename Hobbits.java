//author: Sara Russert
//date: 27 February 2024
//file: Hobbits.java

import java.util.Random;
import java.util.Scanner;
import java.io.*;


public class Hobbits {
   public static void main(String[] args) throws IOException {
      
      final int NUM_HOBBITS = 5;
      final int NUM_MEASUREMENTS = 2;
      
      String fileName = "hobbits.csv";
      
      // call populateHobbits to creat the 2d array
      double[][] hobbits = populateHobbits(NUM_HOBBITS, NUM_MEASUREMENTS);
      
      // display number of hobbits
      System.out.println(hobbits.length + " hobbits accepted Gandalf's invitation to lunch\n");
      
      // calculate the means of the columns
      double[] hobbitMeans = getColMeans(hobbits);
      
      // write hobbits array to file
      writeHobbits(hobbits, fileName);
      
      // read and display the file
      readHobbitses(fileName);
      
      // display the means
      displayColMeans(hobbitMeans);
   }
   
   
   // getRandDouble
   // Return-type: double
   // Parameter: An object of class Random
   public static double getRandDouble(Random randObj) {
      // delcare min and max values for doubles
      final double MIN_RAND_DOUBLE = 0.2;
      final double MAX_RAND_DOUBLE = 0.4;
      boolean validDouble = false;
      double randDouble;
      
      // loop creating random doubles until one falls within range
      do {
      
         randDouble = randObj.nextDouble();
         
         // check to see if rand double is in compliance
         if (randDouble >= MIN_RAND_DOUBLE && randDouble <= MAX_RAND_DOUBLE)
            validDouble = true;
         
      }  while (!validDouble);
      
      return randDouble;
   }
   
   // populateHobbits
   // Return-type: double[ ][ ]
   // Reminder: If you forget the two sets of square brackets, you will have a bug
   // Parameters:
   // An int that represents the number of hobbits (i.e. number of rows)
   // An int that represents the number of columns
   public static double[][] populateHobbits(int numHobbits, int numMeasures) {
   
      // mulitpliers for hobbit measurements
      final double HT_MULTIPLIER = 10.0;
      final double WT_MULTIPLIER = 250.0;
      
      // create new random obj for our random double
      Random rand = new Random();
      
      // create 2d array to store hobbit data
      double[][] hobbitArray = new double[numHobbits][numMeasures];
      
      // assign random dubs to all elements
      for (int i = 0; i < numHobbits; i++) {
         for (int j = 0; j < numMeasures; j++) {
            double randDouble = getRandDouble(rand);
            
            // adjust number by multiplier
            if (j == 0)
               randDouble *= HT_MULTIPLIER;
            else
               randDouble *= WT_MULTIPLIER;
            
            // assign to current space
            hobbitArray[i][j] = randDouble;
         }
      }
      
      return hobbitArray;
   }
   
   
   // writeHobbits
   // Return-type: void
   // Parameters: Two dimensional array, data type double, String fileName
   public static void writeHobbits(double[][] arr, String fileName) throws IOException {
      
      // open the file to write
      PrintWriter outFile = new PrintWriter(fileName);
      
      // print headers for stats
      outFile.println("HEIGHT,WEIGHT");
      
      // loop through array and print stats
      for (int i = 0; i < arr.length; i++) {
         for (int j = 0; j < arr[i].length; j++) {
            outFile.print(arr[i][j]);
            
            // if end of row add new line char
            if (j == arr[i].length -1)
               outFile.print("\n");
            else
               outFile.print(",");
         }
      }
      
      outFile.close();
      System.out.println("The file was successfully written\n");
   }
   
   // readHobbitses
   // Return-type: void
   // Parameter: String fileName
   public static void readHobbitses(String fileName) throws IOException {
      // open file to read
      File dataFile = new File(fileName);
      Scanner inFile = new Scanner(dataFile);
      
      // var to contain the substrings of one line of the file
      String[] oneLine = new String[2];
      
      // track line numbers
      int counter = 0;
      
      System.out.println("Data read from the " + fileName + " file:");
      
      // loop through file lines reading one line at a time
      while(inFile.hasNext()) {
         String dataLine = inFile.nextLine();
         
         oneLine = dataLine.split(",");
         
         if (counter > 0) {
            System.out.printf("%.2f\t\t%.2f\n", Double.parseDouble(oneLine[0]), Double.parseDouble(oneLine[1]));
         } else 
            System.out.println(oneLine[0] + "\t" + oneLine[1]);
         
         if (inFile.hasNext())
            counter++;
      }
      
      inFile.close();
      
      System.out.println("The file has now been successfully read.\n");
   }
   
   
   // getColMeans
   // Return-type: double[ ]
   // Parameter: Two dimensional array; data type double
   public static double[] getColMeans(double[][] arr) {
      // delcare local array to store means
      double[] meansArr = new double[arr[0].length];
      
      // loop through to calculate totals
      for (int i = 0; i < arr.length; i++) {
         for (int j = 0; j < arr[i].length; j++) {
            meansArr[j] += arr[i][j];
         }
      }
      // once all numbers have been added update that value for mean
      for (int i = 0; i < meansArr.length; i++) {
         meansArr[i] /= arr.length;
      }
      
      return meansArr;
   }
   
   
   // displayColMeans
   // Return-type: void
   // Parameter: Single dimensional array; data type double
   public static void displayColMeans(double[] arr) {
      System.out.println("CALCULATED COLUMN MEANS:");
      System.out.println("HEIGHT\tWEIGHT");
      
      for (int i = 0; i < arr.length; i++) {
         System.out.printf("%.2f\t\t", arr[i]);
      }
   }
   
}