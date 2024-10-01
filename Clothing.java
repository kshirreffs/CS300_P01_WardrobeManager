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
    // Stack Overflow - https://stackoverflow.com/questions/3247067/how-do-i-check-that-a-java-string-is-not-all-whitespaces
      // to check if string is only whitespace
    // Zybooks - https://learn.zybooks.com/zybook/WISCCOMPSCI300Spring2024/chapter/3/section/3
      // Exceptions with methods (syntax)
//
///////////////////////////////////////////////////////////////////////////////

import java.time.LocalDate;


public class Clothing {
  
  //fields
  private String brand;
  private String description;
  private LocalDate lastWornDate;
  private int timesWorn;
  
  
  //constructors
  /**
   * @param description a String
   * @param brand a String
   * Constructor for Clothing class
   * @throws IllegalArgumentException with message if description or brand is blank
  */
  // CITE: Exceptions within methods (syntax) - Zybooks
  public Clothing(String description, String brand) {
    //error catching
    // CITE: detecting String with only white space - Stack Overflow
    if (description.trim().length() == 0 || brand.trim().length() == 0) {
      throw new IllegalArgumentException("Description and/or brand needs a value");
    }
    
    this.description = description;
    this.brand = brand;
  }
  
  /**
   * @param description a String
   * @param brand a String
   * @param timesWorn an int
   * @param lastWornDate a LocalDate object
   * Overloaded constructor for Clothing class
   * @throws IllegalArgumentException with message if description or brand is blank
  */
  public Clothing(String description, String brand, int timesWorn, LocalDate lastWornDate) {
    //error catching
    if (description.trim().length() == 0 || brand.trim().length() == 0) {
      throw new IllegalArgumentException("Description and/or brand needs a value");
    }
    
    this.description = description;
    this.brand = brand;
    this.lastWornDate = lastWornDate;
    this.timesWorn = timesWorn;
  }
  
  
  //getters
  /**
   * @return description of Clothing object
  */
  public String getDescription() {
    return description;
  }
  
  /**
   * @return brand of Clothing object
  */
  public String getBrand() {
    return brand;
  }
  
  /**
   * @return lastWornDate of Clothing object
  */
  public LocalDate getLastWornDate() {
    return lastWornDate;
  }
  
  /**
   * @return timesWorn of Clothing object
  */
  public int getNumOfTimesWorn() {
    return timesWorn;
  }
  
  //other methods
  /**
   * @param year an int
   * @param month an int
   * @param day an int
   * Updates number of times the Clothing object has been worn as well as lastWornDate
   * @throws IllegalArgumentException with message if year is < 1 or month is outside [1,12]
  */
  public void wearClothing(int year, int month, int day) throws IllegalArgumentException {
    //error catching
    if (year < 1) throw new IllegalArgumentException("Year value is too small");
    if (month < 1 || month > 12) throw new IllegalArgumentException("Invalid month value");
    
    timesWorn++;
    this.lastWornDate = LocalDate.of(year, month, day);
  }
  
  /**
   * @param o an Object
   * Checks if o equals the Clothing object
   * @return true if o is type Clothing, and brand and descriptions match (not case sensitive)
   * otherwise return false
  */
  public boolean equals(Object o) {
    // check if object is typeClothing
    if (!(o instanceof Clothing)) return false;
    
    // cast to Clothing
    Clothing toCompare = (Clothing) o;
    
    // see if description and brand match (not case sensitive)
    String d1 = this.getDescription().toLowerCase();
    String d2 = toCompare.getDescription().toLowerCase();
    String b1 = this.getBrand().toLowerCase();
    String b2 = toCompare.getBrand().toLowerCase();
    boolean equals = (d1.equals(d2) && b1.equals(b2));
   
    return equals;
  }
  
  /**
   * Creates String representation of Clothing object
   * In format "description, brand, lastWornDate (MM/DD/YYYY), timesWorn"
   * If no lastWornDate, lastWornDate in String is "null"
   * @return String representation of Clothing object
  */
  public String toString() {
    String c = description + "," + brand + ",";
    String date = "null,";
    if (lastWornDate != null) {
      date = "";
      if (lastWornDate.getMonthValue() <= 9) date += "0";
      date += lastWornDate.getMonthValue() + "/";
      if (lastWornDate.getDayOfMonth() <= 9) date += "0";
      date += lastWornDate.getDayOfMonth() + 
          "/" + lastWornDate.getYear() + ",";
    }
    c += date + timesWorn;
    return c;
  }
}
