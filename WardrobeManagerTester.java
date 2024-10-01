//////////////// FILE HEADER //////////////////////////
//
// Title:    P01 Wardrobe Manager
// Course:   CS 300 Spring 2024
//
// Author:   Katelyn Shirreffs
// Email:    kshirreffs@wisc.edu
// Lecturer: Hobbes LeGault
//
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;

// TODO: ALL non-provided method headers!
/**
 * This class tests methods found in Wardrobe Manager
 */
public class WardrobeManagerTester {
  
  //// CONTAINS
  /**
   * test method for verifying whether an oversize array is empty
   * that is, all values in the 2-dimensional array should be null and the integer size value should be 0
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testContainsEmpty() {
    String[][] wardrobe = {null, null, null};
    int size = 0;
    boolean expected = false; // we expect NOT to find the brand/clothing we're looking for
    boolean actual = WardrobeManager.containsClothing("black t-shirt", "Hanes", wardrobe, size); 
      // if we do find the piece of clothing, actual will evaluate to true
    
    if (expected != actual) return false;
    
    return true;
  }
  
  /**
   * PROVIDED: example test method for verifying whether an item is already in the wardrobe.
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testContainsTrue() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"},
        null, null, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 2;
    boolean expected = true;
    boolean actual = WardrobeManager.containsClothing("black t-shirt", "Hanes", wardrobe, size);
    
    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;
    
    // (3) another test method call, this time with case difference (that should still match!)
    actual = WardrobeManager.containsClothing("dark blue jeans", "LEVI", wardrobe, size);
    if (expected != actual) return false;
    
    // (4) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;
    
    // (5) if all of those checks pass, NOW we can say we passed the test
    return true;
  }
  
  /**
   * test method for verifying whether an item is NOT already in the wardrobe.
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testContainsFalse() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"},
        null, null, null};
    int size = 2;
    boolean expected = false;
    boolean actual = WardrobeManager.containsClothing("white t-shirt", "Levi", wardrobe, size);
    
    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;
    
    // (3) if all of those checks pass, NOW we can say we passed the test
    return true;
  }
  
  //// ADD
  
  /**
   * PROVIDED: example test method for adding a new clothing item to an EMPTY oversize array.
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testAddToEmpty() {
    // (1) set up the test variables and call the method we are testing
    String[][] empty = new String[10][];
    int size = 0;
    int expected = 1;
    int actual = WardrobeManager.addClothing("green crop top", "H&M", empty, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;

    // (3) verify that the provided array was updated correctly
    if (empty[0] == null) return false;
    if (!empty[0][0].equalsIgnoreCase("green crop top") || !empty[0][1].equalsIgnoreCase("H&M") ||
        !empty[0][2].equals("never")) return false;
    
    // (4) verify that NOTHING ELSE was changed unexpectedly
    for (int i=1; i<empty.length; i++) {
      if (empty[i] != null) return false;
    }
    
    // (5) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for adding a new clothing item to a NON EMPTY oversize array.
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testAddNonEmpty() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"},
        null, null, null};
    int size = 2;
    int expected = 3;
    int actual = WardrobeManager.addClothing("green crop top", "H&M", wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;

    // (3) verify that the provided array was updated correctly
    if (wardrobe[2] == null) return false;
    if (!wardrobe[2][0].equalsIgnoreCase("green crop top") || !wardrobe[2][1].equalsIgnoreCase("H&M") ||
        !wardrobe[2][2].equals("never")) return false;
    
    // (4) verify that NOTHING ELSE was changed unexpectedly
    for (int i=3; i<wardrobe.length; i++) {
      if (wardrobe[i] != null) return false;
    }
    
    // (5) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for adding a duplicate clothing item to an oversize array.
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testAddDuplicate() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"},
        null, null, null};
    int size = 2;
    int expected = 2; // the method should NOT add the duplicate item
    int actual = WardrobeManager.addClothing("black t-shirt", "Hanes", wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) verify that NOTHING ELSE was changed unexpectedly
    for (int i = 2; i < wardrobe.length; i++) {
      if (wardrobe[i] != null) return false;
    }
    
    // (4) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for adding a clothing item to a full oversize array.
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testAddToFull() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"black t-shirt", "Hanes", "never"}, {"dark blue jeans", "Levi", "never"}};
    int size = 2;
    int expected = 2;
    int actual = WardrobeManager.addClothing("white cargo pants", "Urban Outfitters", wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
  
    return true;
  }
  
  //// INDEX OF
  
  /**
   * test method for finding index when the oversize array is empty
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testIndexOfEmpty() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {null, null, null};
    int size = 0;
    int expected = -1; //no index should be returned
    int actual = WardrobeManager.indexOfClothing("black t-shirt", "Hanes", wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for finding index of items at beginning and end of oversize array
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testIndexOfFirstLast() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Gildan", "2023-10-31"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "asos", "never"}
    };
    int size = 5;
    int expected = 0;
    int actual = WardrobeManager.indexOfClothing("green crop top", "H&M", wardrobe, size);
    
    // (2) verify the expected return value for the first index
    if (expected != actual) return false;
    
    // (3) another test for the last index
    expected = 4; 
    actual = WardrobeManager.indexOfClothing("black halter", "asos", wardrobe, size);
    if (expected != actual) return false;
    
    // (4) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for finding index of items in middle of oversize array
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testIndexOfMiddle() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Gildan", "2023-10-31"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "asos", "never"}
    };
    int size = 5;
    int expected = 2;
    int actual = WardrobeManager.indexOfClothing("cargo pants", "GAP", wardrobe, size);
    
    // (2) verify the expected return value for the first index
    if (expected != actual) return false;
   
    // (3) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for finding index of items in oversize array with no match
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testIndexOfNoMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Gildan", "2023-10-31"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "asos", "never"}
    };
    int size = 5;
    int expected = -1; //no index should be returned (there is no match)
    int actual = WardrobeManager.indexOfClothing("black t-shirt", "Hanes", wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  //// WEAR
  
  /**
   * test method for updating the date last worn of a piece of clothing
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testWearClothingTrue() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Gildan", "2023-10-31"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "asos", "never"}
    };
    int size = 5;
    boolean expected = true;
    boolean actual = WardrobeManager.wearClothing("black t-shirt", "Gildan", "2024-01-27", wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for updating the date last worn of a piece of clothing when there is no match
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testWearClothingNoMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Gildan", "2023-10-31"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "asos", "never"}
    };
    int size = 5;
    boolean expected = false; //we don't have anything to update, should return false
    boolean actual = WardrobeManager.wearClothing("black t-shirt", "Hanes", "2024-01-27", wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  //// BRAND COUNT
  
  /**
   * test method for counting instances of a certain brand, 
   * where all items of clothing are this brand
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testBrandCountAllMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "Gucci", "2024-01-02"}, 
        {"black t-shirt", "gucci", "2023-10-31"},
        {"cargo pants", "GUCCI", "2023-12-29"},
        {"christmas sweater", "gucci", "2023-12-25"},
        {"black halter", "Gucci", "never"}, null
    };
    int size = 5;
    int expected = 5;
    int actual = WardrobeManager.getBrandCount("Gucci", wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for counting instances of a certain brand, 
   * where only some items of clothing are this brand
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testBrandCountSomeMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Gucci", "2023-10-31"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "gucci", "never"}, null, null
    };
    int size = 5;
    int expected = 2;
    int actual = WardrobeManager.getBrandCount("Gucci", wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for counting instances of a certain brand, where no items of clothing are this brand
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testBrandCountNoMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Levi", "2023-10-31"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "asos", "Gucci"}, null, null
    };
    int size = 5;
    int expected = 0;
    int actual = WardrobeManager.getBrandCount("Zara", wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  //// COUNT UNWORN
  
  /**
   * test method for counting how many clothes have never been worn
   * where all items of clothing have never been worn
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testUnwornCountAllMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "never"}, 
        {"black t-shirt", "Gildan", "never"},
        {"cargo pants", "GAP", "never"},
        {"christmas sweater", "handmade", "never"},
        {"black halter", "asos", "never"}, null, null
    };
    int size = 5;
    int expected = 5;
    int actual = WardrobeManager.getNumUnwornClothes(wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for counting how many clothes have never been worn
   * where some items of clothing have never been worn
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testUnwornCountSomeMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Gildan", "never"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "never"},
        {"black halter", "asos", "never"}, null, null
    };
    int size = 5;
    int expected = 3;
    int actual = WardrobeManager.getNumUnwornClothes(wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for counting how many clothes have never been worn
   * where all items of clothing have been worn before
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testUnwornCountNoMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Gildan", "2023-10-31"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "asos", "2023-12-03"}
    };
    int size = 5;
    int expected = 0;
    int actual = WardrobeManager.getNumUnwornClothes(wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  //// MOST RECENTLY WORN
  
  /**
   * PROVIDED: example test method for verifying that the most recently worn item is found correctly.
   * Note that this tester is not comprehensive; if you wish to verify additional behavior you are
   * welcome to add additional tester methods (please specify such methods to be PRIVATE).
   * 
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testMostRecentlyWorn() {
    // (1) set up the test variables and call the method we are testing - EXACT MATCH
    String[][] wardrobe = {{"black t-shirt", "Hanes", "2023-12-19"}, 
        {"grey UW hoodie", "gildan", "2020-03-16"},
        {"dark blue jeans", "Levi", "2024-01-25"},
        {"green cabled sweater", "handmade", "never"}, null};
    String[][] wardrobeCopy = Arrays.copyOf(wardrobe, wardrobe.length);
    int size = 4;
    int expected = 2;
    int actual = WardrobeManager.getMostRecentlyWorn(wardrobe, size);
    
    // (2) verify that the expected return value and the actual return value match
    if (expected != actual) return false;
    
    // (3) since this method should not modify the array, let's check it against our copy:
    if (!Arrays.deepEquals(wardrobe, wardrobeCopy)) return false;
    
    // (4) One more test with a different wardrobe
    String[][] wardrobe2 = {{"black t-shirt", "Hanes", "never"}, 
        {"grey UW hoodie", "gildan", "2020-03-16"},
        {"dark blue jeans", "Levi", "2024-01-25"},
        {"green cabled sweater", "handmade", "never"}, null};
    size = 4;
    expected = 2;
    actual = WardrobeManager.getMostRecentlyWorn(wardrobe2, size);
    if (expected != actual) return false;
    
    // (5) Another test with a different wardrobe
    String[][] wardrobe3 = {{"black t-shirt", "Hanes", "2024-01-29"}, 
        {"grey UW hoodie", "gildan", "2020-03-16"},
        {"dark blue jeans", "Levi", "2024-01-25"},
        {"green cabled sweater", "handmade", "never"}, null};
    size = 4;
    expected = 0;
    actual = WardrobeManager.getMostRecentlyWorn(wardrobe3, size);
    if (expected != actual) return false;
    
    // (5) if all of those checks pass, NOW we can say we passed the test
    return true;
  }
  
  //// REMOVE BY INDEX
  
  /**
   * test method for removing an item of clothing at the last index
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testRemoveLastItem() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Gildan", "2023-10-31"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "asos", "2023-12-03"}, null
    };
    int size = 5;
    int lastIndex = 4;
    int expected = 4;
    int actual = WardrobeManager.removeClothingAtIndex(lastIndex, wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for removing an item of clothing at the first index
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testRemoveFirstItem() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Gildan", "2023-10-31"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "asos", "2023-12-03"}, null
    };
    int size = 5;
    int firstIndex = 0;
    int expected = 4;
    int actual = WardrobeManager.removeClothingAtIndex(firstIndex, wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) verify items have shifted down
    String[][] updatedWardrobe = {{"black t-shirt", "Gildan", "2023-10-31"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "asos", "2023-12-03"}, null, null
    };
    
    if (!Arrays.deepEquals(wardrobe, updatedWardrobe)) return false;
    
    // (4) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for removing an item of clothing at a bad index
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testRemoveBadIndex() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Gildan", "2023-10-31"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "asos", "2023-12-03"}, null
    };
    int size = 5;
    int index = -1;
    int expected = 5; //size shouldn't change
    int actual = WardrobeManager.removeClothingAtIndex(index, wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) other bad index checks
    index = 6; // a null value
    actual = WardrobeManager.removeClothingAtIndex(index, wardrobe, size);
    if (expected != actual) return false;
    
    index = 6; // an out of bounds value
    actual = WardrobeManager.removeClothingAtIndex(index, wardrobe, size);
    if (expected != actual) return false;
    
    // (4) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  //// REMOVE ALL UNWORN
  
  /**
   * test method for removing an item of clothing that hasn't been worn
   * where all items have been worn
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testRemoveUnwornNoMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Gildan", "2023-10-31"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "asos", "2023-12-03"}, null
    };
    int size = 5;
    int expected = 5; //nothing will be removed
    int actual = WardrobeManager.removeAllUnworn(wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for removing an item of clothing that hasn't been worn
   * where only some items have been worn
   * all items should shift down after removal of some items
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testRemoveUnwornSomeMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"black t-shirt", "Gildan", "never"},
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        {"black halter", "asos", "never"}, null
    };
    int size = 5;
    int expected = 3; //only 2 things will be removed
    int actual = WardrobeManager.removeAllUnworn(wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) verify items have shifted down
    String[][] updatedWardrobe = {{"green crop top", "H&M", "2024-01-02"}, 
        {"cargo pants", "GAP", "2023-12-29"},
        {"christmas sweater", "handmade", "2023-12-25"},
        null, null, null
    };
    
    if (!Arrays.deepEquals(wardrobe, updatedWardrobe)) return false;
    
    // (4) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }
  
  /**
   * test method for removing an item of clothing that hasn't been worn
   * where all items have been worn
   * @return false if any of the conditions we are verifying are not what we expect; true ONLY if
   *         all of our expectations are correct
   */
  public static boolean testRemoveUnwornAllMatch() {
    // (1) set up the test variables and call the method we are testing
    String[][] wardrobe = {{"green crop top", "H&M", "never"}, 
        {"black t-shirt", "Gildan", "never"},
        {"cargo pants", "GAP", "never"},
        {"christmas sweater", "handmade", "never"},
        {"black halter", "asos", "never"}, null
    };
    int size = 5;
    int expected = 0; //everything will be removed
    int actual = WardrobeManager.removeAllUnworn(wardrobe, size);
    
    // (2) verify the expected return value
    if (expected != actual) return false;
    
    // (3) verify items have shifted down
    String[][] updatedWardrobe = {null, null, null, null, null, null};
    
    if (!Arrays.deepEquals(wardrobe, updatedWardrobe)) return false;
    
    // (4) if all of those checks pass, NOW we can say that we passed the test
    return true;
  }

  /**
   * PROVIDED: calls all tester methods and displays the results of the tests.
   * 
   * All tests are called in the order they were provided in this file. The output of this method
   * will NOT be graded so you may modify it however you wish.
   * 
   * @param args unused
   */
  public static void main(String[] args) {
    System.out.println("CONTAINS:\n  "+(testContainsEmpty()?"pass":"FAIL")+", "+
        (testContainsTrue()?"pass":"FAIL")+", "+(testContainsFalse()?"pass":"FAIL"));
    System.out.println("ADD:\n  "+(testAddToEmpty()?"pass":"FAIL")+", "+(testAddNonEmpty()?"pass":"FAIL")+
        ", "+(testAddDuplicate()?"pass":"FAIL")+", "+(testAddToFull()?"pass":"FAIL"));
    System.out.println("INDEX OF:\n  "+(testIndexOfEmpty()?"pass":"FAIL")+", "+(testIndexOfFirstLast()?"pass":"FAIL")+
        ", "+(testIndexOfMiddle()?"pass":"FAIL")+", "+(testIndexOfNoMatch()?"pass":"FAIL"));
    System.out.println("WEAR:\n  "+(testWearClothingTrue()?"pass":"FAIL")+", "+(testWearClothingNoMatch()?"pass":"FAIL"));
    System.out.println("BRAND COUNT:\n  "+(testBrandCountAllMatch()?"pass":"FAIL")+", "+
        (testBrandCountSomeMatch()?"pass":"FAIL")+", "+(testBrandCountNoMatch()?"pass":"FAIL"));
    System.out.println("UNWORN COUNT:\n  "+(testUnwornCountAllMatch()?"pass":"FAIL")+", "+
        (testUnwornCountSomeMatch()?"pass":"FAIL")+", "+(testUnwornCountNoMatch()?"pass":"FAIL"));
    System.out.println("MOST RECENTLY WORN:\n  "+(testMostRecentlyWorn()?"pass":"FAIL"));
    System.out.println("REMOVE BY INDEX:\n  "+(testRemoveLastItem()?"pass":"FAIL")+", "+
        (testRemoveFirstItem()?"pass":"FAIL")+", "+(testRemoveBadIndex()?"pass":"FAIL"));
    System.out.println("REMOVE UNWORN:\n  "+(testRemoveUnwornNoMatch()?"pass":"FAIL")+", "+
        (testRemoveUnwornSomeMatch()?"pass":"FAIL")+", "+(testRemoveUnwornAllMatch()?"pass":"FAIL"));
  }

}
