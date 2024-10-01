////////////////FILE HEADER //////////////////////////
//
//Title:    PO4 Wardrobe Manager 2.0
//Course:   CS 300 Spring 2024
//
//Author:   Katelyn Shirreffs
//Email:    kshirreffs@wisc.edu
//Lecturer: Hobbes LeGault
//
////////////////////////ASSISTANCE/HELP CITATIONS ////////////////////////////
//
//Persons:         N/A
//Online Sources:  
    // Geeks for Geeks - https://www.geeksforgeeks.org/split-string-java-examples/
       // Split() method for a String
    // Digital Ocean - https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
       // how to read line by line from a file
    // Baeldung - https://www.baeldung.com/java-append-to-file
       // how to write new line to a file
//
///////////////////////////////////////////////////////////////////////////////

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.text.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Wardrobe {
  
  //fields
  private Clothing[] wardrobe;
  private int wardrobeSize;
  
  //constructor
  /**
   * @param capacity an int
   * @throws IllegalArgumentException with message if capacity is non-positive
   * Constructor for Wardrobe class
  */
  public Wardrobe(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be a positive value");
    }
    
    wardrobe = new Clothing[capacity];
  }
  
  //methods
  /**
   * @param description a String
   * @param brand a String
   * Finds and returns Clothing object that matches the description and brand
   * @throws NoSuchElementException with message if clothing doesn't exist
   * @return Clothing object that matches parameters (not case sensitive)
  */
  public Clothing getClothing(String description, String brand) {
    boolean notFound = true;
    Clothing toReturn = null;
    Clothing toFind = new Clothing(description, brand);
    
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i].equals(toFind)) {
        notFound = false;
        toReturn = wardrobe[i];
        break;
      }
    }
    
    if (notFound) {
      throw new NoSuchElementException("No such clothing item found");
    }
    
    return toReturn;
  }
  
  /**
   * @param toAdd a Clothing object
   * Adds Clothing object to end of wardrobe (adds one to wardrobeSize)
   * If not enough room, doubles capacity
   * @throws IllegalArgumentException with message if toAdd is already in wardrobe
  */
  public void addClothing(Clothing toAdd) throws IllegalArgumentException {
    // check if toAdd is already in wardrobe
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i].equals(toAdd)) {
        throw new IllegalArgumentException("That item of clothing is already in the wardrobe");
      }
     }
    
    // check if enough room, if not, double capacity
    if (wardrobeSize == this.capacity()) {
      // need to double capacity
      // copy wardrobe into new array that is twice the length
      Clothing[] wardrobeBig = new Clothing[capacity()*2];
      for (int i = 0; i < wardrobeSize; i++) {
        wardrobeBig[i] = wardrobe[i];
      }
      wardrobe = wardrobeBig;
      wardrobe[wardrobeSize] = toAdd;
    // if enough room, we directly add the Clothing in
    } else {
      wardrobe[wardrobeSize] = toAdd;
    }
    
    wardrobeSize++;
  }
  
  /**
   * @param toWear a Clothing object
   * @param year an int
   * @param month an int
   * @param day an int
   * "Wears" Clothing object equal to the toWear parameter
   * @throws IllegalArgumentException with message if year is < 1 or month is outside [1,12]
  */
  public void wearClothing(Clothing toWear, int year, int month, int day) {
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i].equals(toWear)) {
        wardrobe[i].wearClothing(year, month, day);
        // exception would be thrown in Clothing wearClothing method
      }
    }
  }
  
  /**
   * @return capacity of this wardrobe
  */
  public int capacity() {
    return wardrobe.length;
  }
  
  /**
   * @return size of this wardrobe
  */
  public int size() {
    return wardrobeSize;
  }
  
  /**
   * @param description a String
   * @param brand a String
   * Removes Clothing object from wardrobe that has matching description and brand
   * @throws IllegalStateException with message if wardrobe is empty
   * @throws NoSuchElementException with message if Clothing object isn't in wardrobe
  */
  public void removeClothing(String description, String brand) {
    boolean notFound = true;
    Clothing toRemove = new Clothing(description, brand);
    
    for (int i = 0; i < wardrobeSize; i++) {
      if (toRemove.equals(wardrobe[i])) {
        wardrobe[i] = null; // "removes" item
        notFound = false;
        
        Clothing[] wUpdate = new Clothing[this.capacity()]; // get rid of empty spaces in wardrobe
        int updatedWardrobeIndex = 0; // wardrobe j index and wUpdate index are different in for loop
        for (int j = 0; j < wardrobeSize; j++) {
          if (wardrobe[j] != null) {
            wUpdate[updatedWardrobeIndex] = wardrobe[j];
            updatedWardrobeIndex++;
          }
        }
        
        wardrobe = wUpdate;
        wardrobeSize--;
        break;
      }
    }
    
    if (notFound) {
      throw new NoSuchElementException("No such clothing item found");
    }
  }
  
  /**
   * @param year an int
   * @param month an int
   * @param day an int
   * Removes all Clothing objects from wardrobe with lastWornDate before date in parameters
  */
  public void removeAllClothingWornBefore(int year, int month, int day) {
    LocalDate toCompare = LocalDate.of(year,month,day);
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i].getLastWornDate() == null ||
          wardrobe[i].getLastWornDate().isBefore(toCompare)) {
        this.removeClothing(wardrobe[i].getDescription(), wardrobe[i].getBrand());
        i--;
      }
    }
  }
  
  /**
   * @param threshold an int
   * Removes all Clothing objects that have been worn less than threshold parameter
  */
  public void removeAllClothingWornNumTimes(int threshold) {
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i].getNumOfTimesWorn() < threshold) {
        this.removeClothing(wardrobe[i].getDescription(), wardrobe[i].getBrand());
        i--;
      }
    }
  }
  
  /**
   * @param str a String
   * Creates new Clothing object based on String parameter (must parse through it)
   * @throws ParseException with message if str doesn't have 4 pieces of info, 
   * or if can't convert info to an int or Date object
   * @return Clothing object with info from str
  */
  public static Clothing parseClothing(String str) throws ParseException {
    // CITE: split() method for splitting str at comma - Geeks for Geeks
    String[] data = str.split(",");
    if (data.length != 4) {
      throw new ParseException("Doesn't have 4 pieces of info", 0);
    }
    
    String description = data[0];
    String brand = data[1];
    
    LocalDate date = null;
    if (!data[2].equals("null")) {
      String[] dateVals = data[2].split("/");
      if (dateVals.length != 3) {
        throw new ParseException("Invalid value for date", 2);
      }
      
      try {
        int year = Integer.parseInt(dateVals[2]);
        int day = Integer.parseInt(dateVals[1]);
        int month = Integer.parseInt(dateVals[0]);
        
        date = LocalDate.of(year, month, day);
      } catch(Exception e) {
        throw new ParseException("Invalid value for date", 2);
      }
    }
    
    int timesWorn;
    try {
      timesWorn = Integer.parseInt(data[3]);
    } catch(Exception e) {
      throw new ParseException("Invalid value for times worn", 3);
    }
    
    Clothing c = new Clothing(description, brand, timesWorn, date);
    return c;
  }
  
  /**
   * @param saveFile a file
   * Loads Clothing objects from file into wardrobe
   * @return true if any lines from file are parsed successfully, false otherwise
  */
  public boolean loadFromFile(File saveFile) {
    // CITE: how to read line by line from a file - Digital Ocean
    boolean success = false;
    Clothing c;
    try {
      Scanner scanner = new Scanner(saveFile);

      while (scanner.hasNextLine()) {
        try {
          c = parseClothing(scanner.nextLine());
          this.addClothing(c);
          success = true;
        } catch (ParseException e) {
          System.out.println("Cannot parse line to Clothing object");
        }
      }

      scanner.close();
  } catch (FileNotFoundException e) {
      e.printStackTrace();
      return false;
  }
    return success;
  }
  
  /**
   * @param saveFile a file
   * Saves all Clothing objects in wardrobe to saveFile
   * @return true if the file is saved successfully, false otherwise
  */
  public boolean saveToFile(File saveFile) {
    boolean success = false;
    FileWriter fw;
    try {
      // CITE: how to write a new line to a file - Baeldung
      fw = new FileWriter(saveFile, true);
      for (int i = 0; i < wardrobeSize; i++) {
        fw.write(wardrobe[i].toString());
        if (i != wardrobeSize - 1) {
          fw.write("\n");
        }
      }
      fw.close();
      success = true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    
    return success;
  }
  
  /**
   * @return the wardrobe array
  */
  protected Clothing[] getArray() {
    return wardrobe;
  }
  
  /**
   * Creates String representation of Wardrobe object
   * Each Clothing on new line enclosed in [] brackets 
   * (last line should NOT have a new line character)
   * @return String representation of Wardrobe object
  */
  public String toString() {
    String w = "";
    for (int i = 0; i < wardrobeSize; i++) {
      w += "[" + wardrobe[i].toString() + "]";
      if (i != wardrobeSize - 1) {
        w += "\n";
      }
    }
    return w;
  }

}
