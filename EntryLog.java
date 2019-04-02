// Adds the given information to a log, and can create a new log if the user
// states they want a fresh log. The new log is a text file.

import java.util.*;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class EntryLog {
   
   private double caloriesAte;
   private double caloriesBurnt;
   private Scanner console;
   
   // Initializes a new EntryLog object by setting the caloriesAte and caloriesBurnt.
   public EntryLog(double caloriesAte, double caloriesBurnt) {
      this.caloriesAte = caloriesAte;
      this.caloriesBurnt = caloriesBurnt;
      console = new Scanner(System.in);
   }
   
   // Post: Returns a string representation of the current date.
   public String getDate() {
      System.out.print("Please enter the month. ");
      String month = console.nextLine();
      System.out.print("Please enter the day. ");
      String day = console.nextLine();
      System.out.print("Please enter the year. ");
      String year = console.nextLine();
      return month + " " + day + ", " + year;
   }
   
   // Post: Adds the new information to an existing log file, marking the date,
   //       as well as the calories ate, burned, and total calories for the day.
   public void append(String date) throws IOException {
      System.out.print("Please type in your desired file name. ");
      String fileName = console.nextLine();
      Writer output = new BufferedWriter(new FileWriter(fileName + ".txt", true));
      output.append(date + " : ");
      output.append(System.getProperty("line.separator"));
      output.append("Your total calories ingested today is: " + caloriesAte + " calories.");
      output.append(System. getProperty("line.separator"));
      output.append("Your total calories burned today is: " + caloriesBurnt + " calories.");
      output.append(System. getProperty("line.separator"));
      output.append("Your overall total calories today is: " + (caloriesAte - caloriesBurnt) +
                    " calories.");
      output.append(System. getProperty("line.separator"));
      output.append(System.getProperty("line.separator"));
      output.close();
   }
   
   // Post: Creates a new text log file storing the date, calories ate and burned,
   //       as well as the total calories.
   public void create(String date) throws IOException {
      System.out.print("Please type in your new file name. ");
      String fileName = console.nextLine();
      PrintWriter writer = new PrintWriter(fileName + ".txt", "UTF-8");
      writer.println(date + ":");
      writer.println("Your total calories ingested today is: " + caloriesAte + " calories.");
      writer.println("Your total calories burned today is: " + caloriesBurnt + " calories.");
      writer.println("Your overall total calories today is: " + (caloriesAte - caloriesBurnt) +
                     " calories.");
      writer.println();
      writer.close();
   }
   
   // Post: Asks the user a question, forcing an answer of "y" or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }
}