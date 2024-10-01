//////////////// FILE HEADER //////////////////////////
//
// Title:    P01 Wardrobe Manager
// Course:   CS 300 Spring 2024
//
// Author:   Katelyn Shirreffs
// Email:    kshirreffs@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  
    // W3Schools - https://www.w3schools.com/java/ref_string_equalsignorecase.asp
                  // for non case sensitive string comparison
    //Stack OverFlow - https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
                  // converting string into int
//
///////////////////////////////////////////////////////////////////////////////


//ADD PARAM and RETURN in headers
/**
 * This class contains relevant methods to manage a wardrobe
 */
public class WardrobeManager {
  
  /**
   * method for seeing if a specific item of clothing is a wardrobe
   * @param description a String
   * @param brand a String
   * @param wardrobe a 2D array of String
   * @param size an integer
   * @return true if it is, false if no such piece of clothing is found
   */
  public static boolean containsClothing(String description, String brand, String[][] wardrobe, int size) {
    for (int i = 0; i < size; i++) {
      // CITE: non case sensitive String comparison - W3Schools
      // so comparisons won't care about capitalization
      if (wardrobe[i][0].equalsIgnoreCase(description) && wardrobe[i][1].equalsIgnoreCase(brand)) return true;
    }
    
    return false;
  }
  
  /**
   * method for adding an item of clothing to a wardrobe that is not full (and doesn't have a duplicate)
   * @param description a String
   * @param brand a String
   * @param wardrobe a 2D array of String
   * @param size an integer
   * @return the new size of the wardrobe once item is added (or not)
   */
  public static int addClothing(String description, String brand, String[][] wardrobe, int size) {
    // if the wardrobe is full  OR already contains that piece of clothing  --> return og size
    if (size == wardrobe.length || containsClothing(description, brand, wardrobe, size)) return size;
    
    // add the piece of clothing
    
    int nextOpenSpace = size;
    wardrobe[nextOpenSpace] = new String[]{description, brand, "never"};
    
    return ++size;
  }
  
  /**
   * method for finding the index of a specific item of clothing
   * @param description a String
   * @param brand a String
   * @param wardrobe a 2D array of String
   * @param size an integer
   * @return -1 if no such piece of clothing is found
   */
  public static int indexOfClothing(String description, String brand, String[][] wardrobe, int size) {
    
    for (int i = 0; i < size; i++) {
      if (wardrobe[i][0].equalsIgnoreCase(description) && wardrobe[i][1].equalsIgnoreCase(brand)) {
        return i; //the index
      }
    }
    return -1;
  }
  
  /**
   * method to update the date a piece of clothing was last worn
   * @param description a String
   * @param brand a String
   * @param date a String
   * @param wardrobe a 2D array of String
   * @param size an integer
   * @return true if successfully updated, false if not
   */
  public static boolean wearClothing(String description, String brand, String date, String[][] wardrobe, int size) {
    for (int i = 0; i < size; i++) {
      if (wardrobe[i][0].equalsIgnoreCase(description) && wardrobe[i][1].equalsIgnoreCase(brand)) {
        wardrobe[i][2] = date;
        return true;
      }
    }
    return false;
  }
  
  /**
   * method for counting how often a certain brand is in the wardrobe
   * @param brand a String
   * @param wardrobe a 2D array of String
   * @param size an integer
   * @return count of that brand in the wardrobe (as an int)
   */
  public static int getBrandCount(String brand, String[][] wardrobe, int size) {
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (wardrobe[i][1].equalsIgnoreCase(brand)) count++;
    }
    return count;
  }
  
  /**
   * method for counting how many items of clothing have never been worn
   * @param wardrobe a 2D array of String
   * @param size an integer
   * @return the count of items that have never been worn
   */
  public static int getNumUnwornClothes(String[][] wardrobe, int size) {
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (wardrobe[i][2].equalsIgnoreCase("never")) count++;
    }
    return count;
  }
  
  /**
   * method for finding the piece of clothing that was most recently worn
   * @param wardrobe a 2D array of String
   * @param size an integer
   * @return index of item of clothing most recently worn
   */
  public static int getMostRecentlyWorn(String[][] wardrobe, int size) {
    int indexMostRecent = 0; // this will keep track of the index of clothing most recently worn
    for (int i = 0; i < size; i++) {
      if (wardrobe[indexMostRecent][2].equalsIgnoreCase("never") || 
          compareTimeDifference(wardrobe[i][2], wardrobe[indexMostRecent][2])) {
        indexMostRecent = i;
      }
    }
    return indexMostRecent;
  }
  
  /**
   * method to test which date comes after the other (which is more recent)
   * @param d1 a String
   * @param d2 a String
   * @return true if d1 is a more recent date value than d2, false otherwise
   */
  private static boolean compareTimeDifference(String d1, String d2) {
    if (d2.equalsIgnoreCase("never")) return true; 
      // d1 will automatically be the more recent date if d2 is never
    if (d1.equalsIgnoreCase("never")) return false;
      // d2 will automatically be the more recent date if d1 is never
    
    // break up String dates into years, months, and days
    String d1Year = d1.substring(0, 4);
    String d1Month = d1.substring(5, 7);
    String d1Day = d1.substring(8);
    
    String d2Year = d2.substring(0, 4);
    String d2Month = d2.substring(5, 7);
    String d2Day = d2.substring(8);
    
    // CITE: converting String into int - Stack Overflow
    // so we can compare dates more easily
    
    // convert these Strings into ints
    int d1YearAsInt = Integer.parseInt(d1Year);
    int d1MonthAsInt = Integer.parseInt(d1Month);
    int d1DayAsInt = Integer.parseInt(d1Day);
    
    int d2YearAsInt = Integer.parseInt(d2Year);
    int d2MonthAsInt = Integer.parseInt(d2Month);
    int d2DayAsInt = Integer.parseInt(d2Day);
    
    // add up the above values in terms of days (largest value of days will be the most recent)
    int d1Total = d1YearAsInt*365 + (d1MonthAsInt-1)*30 + d1DayAsInt;
    int d2Total = d2YearAsInt*365 + (d2MonthAsInt-1)*30 + d2DayAsInt;
    
    // compare the dates
    if (d2Total > d1Total) return false;
    
    return true;  // if d1 is actually the bigger date
  }
  
  /**
   * method to assist in the removeClothingAtIndex and removeAllUnworn methods
   * this method will do the actual removing and readjusting of the array
   * @param wardrobe a 2D array of String
   * @param index an integer
   * @return none (void method)
   */
  private static void removeItem(String[][] wardrobe, int index) {
    int updatedWardrobeIndex = 0;
    for (int i = 0; i < wardrobe.length; i++) {
      if (i != index && wardrobe[i] != null) {
        wardrobe[updatedWardrobeIndex] = wardrobe[i];
        updatedWardrobeIndex++;
        }
    }
    // fill the rest of the array with null
    for (int i = updatedWardrobeIndex; i < wardrobe.length; i++) {
      wardrobe[i] = null;
    }
  }

  
  /**
   * method to remove an item of clothing at a specific index
   * @param index an integer
   * @param wardrobe a 2D array of String
   * @param size an integer
   * @return resulting size of array
   */
  public static int removeClothingAtIndex(int index, String[][] wardrobe, int size) {
    if (index >= 0 && index < size) { // makes sure index is within bounds
      removeItem(wardrobe, index);
      size--;
    }
    return size;
  }
  
  /**
   * method that removes all items of clothing that have never been worn
   * @param wardrobe a 2D array of String
   * @param size an integer
   * @return resulting size of the wardrobe
   */
  public static int removeAllUnworn(String[][] wardrobe, int size) {
    for (int i = 0; i < size; i++) {
      if (wardrobe[i][2].equalsIgnoreCase("never")) {
        removeItem(wardrobe, i);
        size--;
        i--; // must recheck that same spot if we removed it
      }
    }
    return size;
  }
  
  /**
   * method to assist in checking an array by printing out its values
   * prints out "null" for null values
   * @param wardrobe a 2D array of String
   * @return nothing (void method)
   */
  private static void printCheck(String[][] wardrobe) {
    for (int i = 0; i < wardrobe.length; i++) {
      if (wardrobe[i] == null) {
        System.out.print("null ");
      } else {
        for (int j = 0; j < wardrobe[i].length; j++) {
          System.out.print(wardrobe[i][j] + " ");
        }
      }
      System.out.println(); // Move to the next line after each row
    }
  }

}