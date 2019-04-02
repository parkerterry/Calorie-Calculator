// This program runs the calorie calculator, it reads in a government issued text
// files, one storing food and that foods calories per serving, and one storing
// workouts and the calories lost in one hour of that workout. This program prompts
// the user to enter their food intake and workouts in a day and calculates the
// calories gained in that day.

import java.util.*;
import java.io.*;

public class CalorieCalculator {
   
   public static final String foodList = "Food.txt";
   public static final String activityList = "Activities.txt";
   public static final String Log = "Log.txt";
   
   public static void main(String[] args) throws FileNotFoundException {
      System.out.println("Welcome to the calorie calculator.");
      System.out.print("We will now attempt to estimate your total calorie intake");
      System.out.println(" for the day.");
      System.out.println();
      
      double caloriesAte = 0;
      double caloriesBurnt = 0;
      
      Calculator caloriesGained = new Calculator(new Scanner(new File(foodList)), foodList);
      if (caloriesGained.yesTo("Do you want to input your food intake for the day")) {
         do {
            System.out.print("Please enter your food, being as specific as possible. ");
            caloriesAte += caloriesGained.calculate();
            System.out.println();
         } while (caloriesGained.yesTo("Do you have more food to enter"));
      }
      System.out.println();
      Calculator caloriesLost = new Calculator(new Scanner(new File(activityList)), activityList);
      if (caloriesLost.yesTo("Do you want to input your activities for the day")) {
         do {
            System.out.print("Please enter your activites, being as specific as possible. ");
            caloriesBurnt += caloriesLost.calculate();
            System.out.println();
         } while (caloriesLost.yesTo("Do you have more activities to enter"));
      }
      System.out.println();
      System.out.print("Today you have ate " + caloriesAte + " calories and burnt ");
      System.out.println(caloriesBurnt + " calories, meaning today you have consumed...");
      System.out.println((caloriesAte - caloriesBurnt) + " calories!!!");
      System.out.println();
      
      EntryLog newLog = new EntryLog(caloriesAte, caloriesBurnt);
      if (newLog.yesTo("Do you want to log this date into your logbook ")) {
         String date = newLog.getDate();
         if (newLog.yesTo("Do you want to add on to your past log ")) {
            try {
               newLog.append(date);
            } catch (IOException E) {
               System.out.println("File could not be found");
            }
         } else {
            try {
               newLog.create(date);
            } catch (IOException E) {
               System.out.println("File could not be found");
            }
         }
         System.out.println("Your log has been created/updated.");
      }
   }
}