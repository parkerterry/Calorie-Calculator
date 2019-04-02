// Calculates the total calories both ingested and burned in any given day,
// as well as appends new food/workouts to their respectful lists, as well
// as the calories from said food/workout.

import java.util.*;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class Calculator {
   
   private Map<String, Double> data;
   private Scanner console;
   private Scanner input;
   private String fileName;
   
   // Pre: Assumes that the text file inputted is not empty.
   // Post: Initializes the calculator object by mapping various foods/workouts
   //       to the calories gained and/or lost.
   public Calculator(Scanner input, String fileName) {
      this.fileName = fileName;
      data = new HashMap<>();
      this.input = input;
      console = new Scanner(System.in);
      while (input.hasNextLine()) {
         String line = input.nextLine();
         String[] parts = line.split(":");
         data.put(parts[0].trim(), Double.parseDouble(parts[1].trim()));
      }
   }
   
   // Post: Calculates the total calories gained/lost;
   public double calculate() {
      String type = console.nextLine();
      if (!contains(type)) {
         System.out.print("Please enter the amount of calories in one serving of this food,");
         System.out.print(" or the amount lost in 30 minutes of this activity. ");
         Double calories = Double.parseDouble(console.nextLine());
         data.put(type, calories);
         try {
            append(type, calories);
         } catch (IOException E) {
            System.out.println("File could not be found");
         }
      }
      System.out.print("Please enter the number of servings eaten, or the number of 30 minute");
      System.out.print(" intervals of the activity done. ");
      Double servings = Double.parseDouble(console.nextLine());
      System.out.println("These calories have been documented!");
      return servings * data.get(type);
   }
   
   // Post: Returns true if the given symbol is a non-terminal of the grammar,
   //       else returns false.
   public boolean contains(String type) {
      return data.containsKey(type);
   }
   
   // Pre: Throws IOException as we know the file is valid.
   // Post: Appends the file by adding a new line containing the new food/
   //       workout and its calorie count.
   public void append(String name, double calories) throws IOException {
      Writer output = new BufferedWriter(new FileWriter(fileName, true));
      output.append(name + " : " + calories);
      output.append(System. getProperty("line.separator"));
      output.close();
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