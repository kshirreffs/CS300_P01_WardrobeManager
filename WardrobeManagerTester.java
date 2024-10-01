import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.text.ParseException;
import java.io.File;
import java.util.Arrays;

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
//Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * A tester class for the Wardrobe Manager project. It contains various tests
 * to check the correctness of the Wardrobe and Clothing classes.
 */
public class WardrobeManagerTester {

  /**
   * Tests both of the Clothing constructors and all getters for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   * 
   * @author Michelle
   */
  public static boolean testClothingConstructorAndGetters() {
    
    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try { 
      // test the 2-argument constructor
      Clothing c = new Clothing("black t-shirt", "Gildan");
      
      // check that the four instance data fields have been initialized correctly
      // using the getters to do this we are also checking their correctness
      // in a bad implementation either 1) the constructor didn't intialize a data field correctly
      // OR 2) the getter doesn't return the correct value
      if (!c.getDescription().equals("black t-shirt")) return false;
      if (!c.getBrand().equals("Gildan")) return false;
      if (c.getNumOfTimesWorn() != 0) return false;
      if (c.getLastWornDate() != null) return false;
      
      // test the 4 argument constructor
      // same idea as the previous test case
      LocalDate date = LocalDate.of(2024,2,14); // create a date object for last worn
      c = new Clothing("jeans", "Levi", 3, date);
      if (!c.getDescription().equals("jeans")) return false;
      if (!c.getBrand().equals("Levi")) return false;
      if (c.getNumOfTimesWorn() != 3) return false;
      if (!c.getLastWornDate().equals(date)) return false;

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  /**
   * Tests that both of the Clothing constructors throw the correct type of exception(s) 
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   * 
   * @author Michelle and Hobbes
   */
  public static boolean testClothingConstructorExceptions() {
    // Here we call constructors with input that should lead to an IllegalArgumentException
    // on a correct (good) implementation. ALL of these cases SHOULD throw exceptions,
    // so we test them in separate try-catch statements to verify that each individual
    // case throws an exception.
    
    try {
      // test the 2 argument constructor with a blank description
      new Clothing(" ", "Gildan");

      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (IllegalArgumentException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }

    try {
      // and make sure a blank brand will also throw an exception
      new Clothing("black t-shirt", "  ");

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }
      
    try {
      // test the 4 argument constructor with a blank description
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing(" ", "Gildan", 4, date);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    try {
      // and verifying that a blank brand will also throw an exception
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing("black t-shirt", "  ", 6, date);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message,
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }
    
    // passed all the tests!
    return true;
  }
  
  /**
   * Tests for the correctness of the Clothing classes' wearClothing() method.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   * 
   */
  public static boolean testClothingWear() {
    LocalDate date = LocalDate.of(2021, 12, 25);
    Clothing testClothing = new Clothing("black jeans", "Levi's", 4, date);
    
    // TEST 1: Call wearClothing with valid inputs    
    // should update testClothing's lastWornDate and timesWorn
    testClothing.wearClothing(2023, 2, 24);
    
    // double check this to be true, return false otherwise
    if (testClothing.getNumOfTimesWorn() != 5) return false;
    if (!testClothing.getLastWornDate().equals(LocalDate.of(2023, 2, 24))) return false;
    
    
    // TEST 2: Call wearClothing with input that should lead to an IllegalArgumentException
    try {
      // invalid year input (less than 1)
      testClothing.wearClothing(-20, 2, 31);
      
      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (IllegalArgumentException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }

    try {
      // invalid month input (not in [1,12])
      testClothing.wearClothing(2023, 13, 24);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }
    
    // passed all the tests!
    return true;
  }

  /**
   * Tests the Wardrobe constructor and all getters for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */

  public static boolean testWardrobeConstructorAndGetters() {
    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try { 
      // test the constructor
      Wardrobe w = new Wardrobe(4);
      
      // check that the one instance data field has been initialized correctly
      // using the getters to do this we are also checking their correctness
      // in a bad implementation either 1) the constructor didn't intialize a data field correctly
      // OR 2) the getter doesn't return the correct value
      if (w.capacity() != 4) return false;
      if (w.size() != 0) return false;
      Clothing[] wArray = new Clothing[4];
      if (!Arrays.deepEquals(wArray, w.getArray())) return false;

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  /**
   * Tests that the Wardrobe constructor throws the correct type of exception(s) 
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testWardrobeConstructorExceptions() {
    // Here we call constructors with input that should lead to an IllegalArgumentException
    // on a correct (good) implementation. ALL of these cases SHOULD throw exceptions,
    
    try {
      // test the constructor with a negative capacity
      new Wardrobe(-2);

      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (IllegalArgumentException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }

    try {
      // and make sure a 0 capacity will also throw an exception
      new Wardrobe(0);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }
    
    // passed all the tests!
    return true;
  }
  
  /**
   * Tests that the Wardrobe's addClothing() method throws the correct type of exception(s) 
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddClothingExceptions() {
    // Here we call constructors with input that should lead to an IllegalArgumentException
    // on a correct (good) implementation. ALL of these cases SHOULD throw exceptions,
    
    try {
      // test adding a duplicate item
      Wardrobe w = new Wardrobe(3);
      Clothing c = new Clothing("Squid hat", "Hendricksen and Co");
      w.addClothing(c);
      LocalDate date = LocalDate.of(2004,9,19);
      Clothing c2 = new Clothing("squid hat", "hendricksen and co", 23, date);
      w.addClothing(c2);

      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (IllegalArgumentException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }
    
    // passed all the tests!
    return true;
  }
  
  /**
   * Tests the Wardrobe's addClothing() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddClothing() {
    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    
    // TEST 1: adding clothing to wardrobe with enough room
    try { 
      Wardrobe w = new Wardrobe(2);
      Clothing c1 = new Clothing("Squid hat", "Hendricksen and Co");
      LocalDate date = LocalDate.of(2004,9,19);
      Clothing c2 = new Clothing("black jeans", "Levi's", 4, date);
      
      w.addClothing(c1);
      w.addClothing(c2);
      
      if (!w.getArray()[0].equals(c1)) return false;
      if (!w.getArray()[1].equals(c2)) return false;
      if (w.size() != 2) return false;

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }
    
    // TEST 2: adding clothing to a wardrobe that is too small (should double in capacity)
    try { 
      Wardrobe w = new Wardrobe(2);
      Clothing c1 = new Clothing("Squid hat", "Hendricksen and Co");
      LocalDate date = LocalDate.of(2004,9,19);
      Clothing c2 = new Clothing("black jeans", "Levi's", 4, date);
      Clothing c3 = new Clothing("Blue t-shirt", "ZARA");
      
      w.addClothing(c1);
      w.addClothing(c2);
      w.addClothing(c3);
      
      if (!w.getArray()[0].equals(c1)) return false;
      if (!w.getArray()[1].equals(c2)) return false;
      if (!w.getArray()[2].equals(c3)) return false;
      if (w.size() != 3) return false;
      if (w.capacity() != 4) return false;

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  
  /**
   * Tests the Wardrobe's getClothing() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetClothing() {
    try { 
      Wardrobe w = new Wardrobe(2);
      Clothing c1 = new Clothing("Squid hat", "Hendricksen and Co");
      LocalDate date = LocalDate.of(2004,9,19);
      Clothing c2 = new Clothing("black jeans", "Levi's", 4, date);
      
      w.addClothing(c1);
      w.addClothing(c2);
      
      Clothing toFind = w.getClothing("squid hat", "hendricksen and co");
      if (!toFind.equals(c1)) return false;

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  
  /**
   * Tests that the Wardrobe's getClothing() method throws the correct type of exception(s) 
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetClothingExceptions() {
    try {
      Wardrobe w = new Wardrobe(2);
      Clothing c1 = new Clothing("Squid hat", "Hendricksen and Co");
      LocalDate date = LocalDate.of(2004,9,19);
      Clothing c2 = new Clothing("black jeans", "Levi's", 4, date);
      
      w.addClothing(c1);
      w.addClothing(c2);
      
      Clothing c = w.getClothing("white slacks", "Free People");

      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (NoSuchElementException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests that the Wardrobe's removeClothing() method throws the correct type of exception(s) 
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveClothingExceptions() {
    try {
      Wardrobe w = new Wardrobe(2);
      Clothing c1 = new Clothing("Squid hat", "Hendricksen and Co");
      LocalDate date = LocalDate.of(2004,9,19);
      Clothing c2 = new Clothing("black jeans", "Levi's", 4, date);
      
      w.addClothing(c1);
      w.addClothing(c2);
      
      w.removeClothing("white slacks", "Free People");

      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (NoSuchElementException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the Wardrobe's removeClothings() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveClothing() {
    try { 
      Wardrobe w = new Wardrobe(2);
      Clothing c1 = new Clothing("Squid hat", "Hendricksen and Co");
      LocalDate date = LocalDate.of(2004,9,19);
      Clothing c2 = new Clothing("black jeans", "Levi's", 4, date);
      
      w.addClothing(c1);
      w.addClothing(c2);
      
      w.removeClothing("squid hat", "hendricksen and co");
      
      Clothing[] expected = new Clothing[w.capacity()];
      expected[0] = c2;
      if (!Arrays.deepEquals(expected, w.getArray())) return false;
      if (w.size() != 1) return false;
      

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the Wardrobe's removeAllClothingWornBefore() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornBefore() {
    try { 
      Wardrobe w = new Wardrobe(5);
      LocalDate date1 = LocalDate.of(2008,9,19);
      Clothing c1 = new Clothing("Squid hat", "Hendricksen and Co", 4, date1);
      LocalDate date2 = LocalDate.of(2004,9,19);
      Clothing c2 = new Clothing("black jeans", "Levi's", 4, date2);
      LocalDate date3 = LocalDate.of(2010,9,19);
      Clothing c3 = new Clothing("white jeans", "FP", 4, date3);
      Clothing c4 = new Clothing("white shirt", "ZARA");
      
      w.addClothing(c1);
      w.addClothing(c2);
      w.addClothing(c3);
      w.addClothing(c4);
      
      w.removeAllClothingWornBefore(2009, 7, 26);
      
      Clothing[] expected = new Clothing[w.capacity()];
      expected[0] = c3;
      if (!Arrays.deepEquals(expected, w.getArray())) return false;
      if (w.size() != 1) return false;
      

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the Wardrobe's removeAllClothingWornNumTimes() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornNumTimes() {
    try { 
      Wardrobe w = new Wardrobe(5);
      LocalDate date1 = LocalDate.of(2008,9,19);
      Clothing c1 = new Clothing("Squid hat", "Hendricksen and Co", 4, date1);
      LocalDate date2 = LocalDate.of(2004,9,19);
      Clothing c2 = new Clothing("black jeans", "Levi's", 14, date2);
      LocalDate date3 = LocalDate.of(2010,9,19);
      Clothing c3 = new Clothing("white jeans", "FP", 23, date3);
      Clothing c4 = new Clothing("white shirt", "ZARA");
      
      w.addClothing(c1);
      w.addClothing(c2);
      w.addClothing(c3);
      w.addClothing(c4);
      
      w.removeAllClothingWornNumTimes(11);
      
      Clothing[] expected = new Clothing[w.capacity()];
      expected[0] = c2;
      expected[1] = c3;
      if (!Arrays.deepEquals(expected, w.getArray())) return false;
      if (w.size() != 2) return false;
      

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests that the Wardrobe's parseClothing() method throws the correct type of exception(s) 
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothingExceptions() {
    
    // TEST 1: not enough info
    try {
      Clothing c = Wardrobe.parseClothing("black shirt,ZARA,3");
      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (ParseException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }
    
    // TEST 2: incorrect date value
    try {
      Clothing c = Wardrobe.parseClothing("black shirt,ZARA,04/2004,2");
      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (ParseException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }
    
    // TEST 3: incorrect date value
    try {
      Clothing c = Wardrobe.parseClothing("black shirt,ZARA,february/4/two,2");
      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (ParseException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }
    
    // TEST 4: incorrect timesWorn value
    try {
      Clothing c = Wardrobe.parseClothing("black shirt,ZARA,04/17/1998,four");
      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (ParseException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests the Wardrobe's parseClothing method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothing() {
    try { 
      Clothing c = Wardrobe.parseClothing("black shirt,ZARA,04/01/2004,2");
      if (!c.getDescription().equals("black shirt")) return false;
      if (!c.getBrand().equals("ZARA")) return false;
      LocalDate d = LocalDate.of(2004, 04, 01);
      if (!d.equals(c.getLastWornDate())) return false;
      if (c.getNumOfTimesWorn() != 2) return false;
      
      Clothing c2 = Wardrobe.parseClothing("jeans,Levi's,null,0");
      if (!c2.getDescription().equals("jeans")) return false;
      if (!c2.getBrand().equals("Levi's")) return false;
      if (c2.getLastWornDate() != null) return false;
      if (c2.getNumOfTimesWorn() != 0) return false;

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  /**
   * Runs all testing methods and prints out their results.
   * @return true if and only if all the tests return true, false otherwise
   */
  public static boolean runAllTests() {
    boolean test1 = testClothingConstructorExceptions();
    System.out.println("testClothingConstructorExceptions(): " + (test1 ? "pass" : "FAIL"));
    
    boolean test2 = testClothingConstructorAndGetters();
    System.out.println("testClothingConstructorAndGetters(): " + (test2 ? "pass" : "FAIL"));
    
    boolean test3 = testClothingWear();
    System.out.println("testClothingWear(): " + (test3 ? "pass" : "FAIL"));
    
    boolean test4 = testWardrobeConstructorAndGetters();
    System.out.println("testWardrobeConstructorAndGetters(): " + (test4 ? "pass" : "FAIL"));
    
    boolean test5 = testWardrobeConstructorExceptions();
    System.out.println("testWardrobeConstructorExceptions(): " + (test5 ? "pass" : "FAIL"));
    
    boolean test6 = testAddClothingExceptions();
    System.out.println("testAddClothingExceptions(): " + (test6 ? "pass" : "FAIL"));
    
    boolean test7 = testAddClothing();
    System.out.println("testAddClothing(): " + (test7 ? "pass" : "FAIL"));
    
    boolean test8 = testGetClothing();
    System.out.println("testGetClothing(): " + (test8 ? "pass" : "FAIL"));
    
    boolean test9 = testGetClothingExceptions();
    System.out.println("testGetClothingExceptions(): " + (test9 ? "pass" : "FAIL"));
    
    boolean test10 = testRemoveClothing();
    System.out.println("testRemoveClothing(): " + (test10 ? "pass" : "FAIL"));
    
    boolean test11 = testRemoveClothingExceptions();
    System.out.println("testRemoveClothingExceptions(): " + (test11 ? "pass" : "FAIL"));
    
    boolean test12 = testRemoveAllClothingWornBefore();
    System.out.println("testRemoveAllClothingWornBefore(): " + (test12 ? "pass" : "FAIL"));
    
    boolean test13 = testRemoveAllClothingWornNumTimes();
    System.out.println("testRemoveAllClothingWornNumTimes(): " 
        + (test13 ? "pass" : "FAIL"));

    boolean test14 = testParseClothingExceptions();
    System.out.println("testParseClothingExceptions(): " + (test14 ? "pass" : "FAIL"));

    boolean test15 = testParseClothing();
    System.out.println("testParseClothing(): " + (test15 ? "pass" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
            && test11 && test12 && test13 && test14 && test15;
  }
  
  public static void main(String[] args) {
    System.out.println("runAllTests(): " + runAllTests());
  }
}
