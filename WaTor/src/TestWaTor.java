//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: WaTor
// Files: a list of all source files used by that program
// Course: 200,Spring, 2018
//
// Author:           Omar Kurosu Jalil
// Email:            kurosujalil@wisc.edu
// CS Login:         kurosu-jalil
// Lecturer's Name:  Mr. Marc Renault
// Lab Section:      
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


/**
 * This file contains testing methods for the WaTor project.
 * These methods are intended to serve several objectives:
 * 1) provide an example of a way to incrementally test your code
 * 2) provide example method calls for the WaTor methods
 * 3) provide examples of creating, accessing and modifying arrays
 *    
 * Toward these objectives, the expectation is that part of the 
 * grade for the WaTor project is to write some tests and
 * write header comments summarizing the tests that have been written. 
 * Specific places are noted with TODO but add any other comments 
 * you feel would be useful.
 * 
 * Some of the provided comments within this file explain
 * Java code as they are intended to help you learn Java.  However,
 * your comments and comments in professional code, should
 * summarize the purpose of the code, not explain the meaning
 * of the specific Java constructs.
 *    
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class contains a few methods for testing methods in the WaTor
 * class as they are developed. These methods are all private as they are only
 * intended for use within this class.
 * 
 * @author Jim Williams
 * @author TODO add your name here when you add tests and comment the tests
 *
 */
public class TestWaTor {
    
    /**
     * This is the main method that runs the various tests. Uncomment the tests
     * when you are ready for them to run.
     * 
     * @param args  (unused)
     */
    public static void main(String []args) {

        //milestone 1
        testClearMoves();
        testEmptyArray();
        testCountCreatures();
        
        //The best way to test the following is probably to compare
        //output with the examples.
        //showFishAndSharks
        //placeFish        
        //placeSharks      
       
        //milestone 2
       testUnoccupiedPositions();
      testChooseMove();
        testFishPositions();
        
        //test code for fishPositions would be similar to test code for unoccupiedPositions
        //test fishSwimAndBreed and sharksHuntAndBreed may be easiest
        //by comparing output to examples.
        
        //milestone 3
        //comparing results of either inputting from or outputting to files
        //may be the easiest way to test.
    }
    
    /**
     * Compares the lists to see if they are the same size and contain the same elements.
     * @param list1 One list of coordinates.
     * @param list2 2nd list of coordinates
     * @return Whether the lists contain the same coordinates or not.
     */
    private static boolean matchingArrayLists(ArrayList<int[]>list1, ArrayList<int[]>list2) {
        boolean result = true;
        if ( list1.size() != list2.size()) {// compares size
            System.err.println("list1 size: " + list1.size() + " list2 size:" + list2.size() + " should be the same");
             result = false;
             return result;
        } 
        for ( int i = 0; i < list1.size(); i++) {
            int[]move1 = list1.get(i);
            int[]move2 = list2.get(i);
            if ( move1[0] == move2[0] && move1[1] == move2[1]) {// compares values
                
            } else {
                result = false;
                System.err.println( "list1("+ i+"):" + Arrays.toString(move1) + " doesn't match in list2: " + Arrays.toString( move2));
               
                break;
            }
        }
        return result;
    }
    
    /**
     * 
     * Checks the immediate surrounding coordinates of a selected row and col
     * to see if there are fish or sharks at that location or not. If the two different 
     * ArrayLists (positions and expected) are different then the test fails and an error 
     * message is printed. Meaning that one of the coordinates was not checked correctly. 
     * 
     * @param fish integer array with locations of different fish.
     * @param shark integer array with locations of different sharks.
     * @param row integer location of a specific row
     * @param col integer location of a specific column
     */
    private static void testUnoccupiedPositions() {
        boolean error = false;
        
        int [][]fish = new int[][]{{-1,-1,-1},{-1,0,-1},{-1,-1,-1}};
        int [][]sharks = new int[][]{{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};// adds values to array to check if it can find unnocupied posns
        
        ArrayList<int[]> positions = WaTor.unoccupiedPositions( fish, sharks, 1, 1);
        ArrayList<int[]>expected = new ArrayList<>();
        expected.add( new int[]{0,1});
        expected.add( new int[]{2,1});// expected result
        expected.add( new int[]{1,0});
        expected.add( new int[]{1,2});
        if ( !matchingArrayLists( expected, positions)) {// checks if expected matches actual
            error = true;
            System.err.println("testUnoccupiedPositions 1 :" );
        }
        
        positions = WaTor.unoccupiedPositions( fish, sharks, 0, 1);
        expected = new ArrayList<>();//expected result
        expected.add( new int[]{2,1});
        expected.add( new int[]{0,0});
        expected.add( new int[]{0,2});
        if ( !matchingArrayLists( expected, positions)) {// checks if actual matches expecte
            error = true;
            System.err.println("testUnoccupiedPositions 2 :" );
        }
        
        positions = WaTor.unoccupiedPositions( fish, sharks, 0, 0);
        expected = new ArrayList<>();
        expected.add( new int[]{2,0});//expected result array
        expected.add( new int[]{1,0});
        expected.add( new int[]{0,2});
        expected.add( new int[]{0,1});
        if ( !matchingArrayLists( expected, positions)) {// compares actual with expected
            error = true;
            System.err.println("testUnoccupiedPositions 3 :" );
        }        
        // following tests check for different cases of when there are fish or sharks in different positions 
        // in some cases it checks if the method can recognize when there are no fish or sharks 
        fish = new int[][]{{0,0,0},{0,0,0},{0,0,0}};
        sharks = new int[][]{{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};
        
        positions = WaTor.unoccupiedPositions( fish, sharks, 1, 1);
        expected = new ArrayList<>();

        if ( !matchingArrayLists( expected, positions)) {
            error = true;
            System.err.println("testUnoccupiedPositions 4 :" );
        }
        
        positions = WaTor.unoccupiedPositions( fish, sharks, 0, 1);
        expected = new ArrayList<>();

        if ( !matchingArrayLists( expected, positions)) {
            error = true;
            System.err.println("testUnoccupiedPositions 5 :" );
        }
        
        positions = WaTor.unoccupiedPositions( fish, sharks, 0, 0);
        expected = new ArrayList<>();

        if ( !matchingArrayLists( expected, positions)) {
            error = true;
            System.err.println("testUnoccupiedPositions 6 :" );
        }        
        fish = new int[][]{{0,0,0},{-1,0,0},{0,0,0}};
        sharks = new int[][]{{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};
        
        positions = WaTor.unoccupiedPositions( fish, sharks, 1, 1);
        expected = new ArrayList<>();
        expected.add( new int[]{1,0});

        if ( !matchingArrayLists( expected, positions)) {
            error = true;
            System.err.println("testUnoccupiedPositions 7 :" );
        }
        
        positions = WaTor.unoccupiedPositions( fish, sharks, 0, 1);
        expected = new ArrayList<>();
        if ( !matchingArrayLists( expected, positions)) {
            error = true;
            System.err.println("testUnoccupiedPositions 8 :" );
        }
        
        positions = WaTor.unoccupiedPositions( fish, sharks, 0, 0);
        expected = new ArrayList<>();
        expected.add( new int[]{1,0});
        if ( !matchingArrayLists( expected, positions)) {
            error = true;
            System.err.println("testUnoccupiedPositions 9 :" );
        }
        
        if ( error) {
            System.err.println("testUnoccupiedPositions failed");
        } else {
            System.out.println("testUnoccupiedPositions passed");            
        }
    }
    
    /**
     * Checks the immediate surrounding coordinates of a selected row and col that contains
     * a fish to make sure the surrounding location is precise. If the two different 
     * ArrayLists (positions and expected) are different then the test fails and an error 
     * message is printed. Meaning that one of the coordinates was not checked correctly. 
     * 
     * @param fish integer array with locations of different fish.
     * @param row integer location of a specific row for a fish
     * @param col integer location of a specific column for a fish
     *
     */
    private static void testFishPositions() {
        boolean error = false;
        
        int [][]fish = new int[][]{{-1,-1,-1},{-1,0,-1},{-1,-1,-1}};
        
        ArrayList<int[]> positions = WaTor.fishPositions(fish, 1, 1);
        ArrayList<int[]>expected = new ArrayList<>();
        expected.add( new int[]{0,1});// creates an array with expected output
        expected.add( new int[]{2,1});
        expected.add( new int[]{1,0});
        expected.add( new int[]{1,2});
        if ( !matchingArrayLists( expected, positions)) {// compares result with expected
            error = true;
            System.err.println("testFishPositions 1 :" );
        }
        
        positions = WaTor.fishPositions( fish, 0, 1);
        expected = new ArrayList<>();
        expected.add( new int[]{2,1});// creates an array with expected output
        expected.add( new int[]{0,0});
        expected.add( new int[]{0,2});
        if ( !matchingArrayLists( expected, positions)) {// compares result with expected
            error = true;
            System.err.println("testFishPositions 2 :" );
        }
        
        positions = WaTor.fishPositions(fish, 0, 0);
        expected = new ArrayList<>();
        expected.add( new int[]{2,0});// creates an array with expected output
        expected.add( new int[]{1,0});
        expected.add( new int[]{0,2});
        expected.add( new int[]{0,1});
        if ( !matchingArrayLists( expected, positions)) {// compares result with expected
            error = true;
            System.err.println("testFishPositions 3 :" );
        } 
        // the following tests check different cases of what array fish contains:
        //different ages or no fish in some cases
        fish = new int[][]{{0,0,0},{0,0,0},{0,0,0}};
        
        positions = WaTor.fishPositions(fish, 1, 1);
        expected = new ArrayList<>();

        if ( !matchingArrayLists( expected, positions)) {// compares result with expected
            error = true;
            System.err.println("testFishPositions 4 :" );
        }
        
        positions = WaTor.fishPositions( fish, 0, 1);
        expected = new ArrayList<>();

        if ( !matchingArrayLists( expected, positions)) {// compares result with expected
            error = true;
            System.err.println("testFishPositions 5 :" );
        }
        
        positions = WaTor.fishPositions( fish, 0, 0);
        expected = new ArrayList<>();

        if ( !matchingArrayLists( expected, positions)) {// compares result with expected
            error = true;
            System.err.println("testFishPositions 6 :" );
        }
        
        fish = new int[][]{{0,0,0},{-1,0,0},{0,0,0}};
        
        positions = WaTor.fishPositions(fish, 1, 1);
        expected = new ArrayList<>();
        expected.add(new int[]{1,0});

        if ( !matchingArrayLists( expected, positions)) {// compares result with expected
            error = true;
            System.err.println("testFishPositions 7 :" );
        }
        
        positions = WaTor.fishPositions(fish, 0, 1);
        expected = new ArrayList<>();
        if ( !matchingArrayLists( expected, positions)) {
            error = true;
            System.err.println("testFishPositions 8 :" );
        }
        
        positions = WaTor.fishPositions( fish, 0, 0);
        expected = new ArrayList<>();
        expected.add( new int[]{1,0});
        if ( !matchingArrayLists( expected, positions)) {// compares result with expected
            error = true;
            System.err.println("testFishPositions 9 :" );
        }
        
        if (error) {
            System.err.println("testFishPositions failed");
        } else {
            System.out.println("testFishPositions passed");            
        }
        //TODO Implement in Milestone 2
    }
    /**
     * Checks the neighbor ArrrayList for different situations. There are are two 
     * possible circumstances:
     * 
     * 1 - the ArrayList neighbor selected location is zero so chooseMove should return
     * null. If this does not occur then there is an error
     * 2 - the ArrayList neighbor selected location is an element other than zero choose move
     * should return oneMove. If this does not occur then there is an error.  
     * 
     * @param neighbor integer ArrayList with a bunch of fish or sharks to choose from
     * @param random number generator
     *
     */    
    private static void testChooseMove() {
        boolean error = false;
        Random randGen = new Random();
        randGen.setSeed( 456);//expected seed to get same "random" values
        
        ArrayList<int[]> input = new ArrayList<>();
        int [] expected = null;//expected
        int [] result = WaTor.chooseMove( input, randGen);//actual
        if ( result != expected) {// compares them
            error = true;
            System.err.println("testChooseMove 0: result not null");
        }
        
        input.clear();
        int [] oneMove = new int[] {0,1};
        input.add(oneMove);// creates and expected result
        expected = oneMove;
        result = WaTor.chooseMove( input, randGen);
        if ( result != expected) {// comapares expected with result
            error = true;
            System.err.println("testChooseMove 1: result not " + Arrays.toString( oneMove));
        }
        
        input.clear();
        int [] move1 = new int[] {0,1};
        int [] move2 = new int[] {1,0};
        input.add( move1);// different case with different values for expected
        input.add( move2);
        int move1Count = 0;
        int move2Count = 0;
        int numTrials = 1000;
        for ( int i = 0; i < numTrials; i++) {
            result = WaTor.chooseMove( input, randGen);
            if ( result == move1) move1Count++; // increments if result gives expected value
            else if ( result == move2) move2Count++;// increments if result gives expected value
        }
        if ( move1Count != 513 || move2Count != 487 ) {// compares the counts with the expected counts.
            error = true;
            System.err.println("testChooseMove 2: expected 513,487 move1Count=" + move1Count + " move2Count=" + move2Count);
        }
        
        input.clear();
        move1 = new int[] {0,1};
        move2 = new int[] {1,0};
        int[] move3 = new int[] {2,1};
        input.add( move1);
        input.add( move2);
        input.add( move3);
        move1Count = 0;
        move2Count = 0;
        int move3Count = 0;
        numTrials = 1000;
        for ( int i = 0; i < numTrials; i++) {
            result = WaTor.chooseMove( input, randGen);
            if ( result == move1) move1Count++; 
            else if ( result == move2) move2Count++;// increments count if result is correct
            else if ( result == move3) move3Count++;
        }
        if ( move1Count != 325 || move2Count != 341 || move3Count != 334 ) {// checks count to see it found all correct moves
            error = true;
            System.err.println("testChooseMove 3: expected 325,341,334 move1Count=" + move1Count + " move2Count=" + move2Count + " move3Count=" + move3Count);
        }
        
        input.clear();
        move1 = new int[] {0,1};
        move2 = new int[] {1,0};
        move3 = new int[] {2,1};
        int []move4 = new int[] {1,2};
        input.add( move1);
        input.add( move2);
        input.add( move3);
        input.add( move4);
        move1Count = 0;
        move2Count = 0;
        move3Count = 0;
        int move4Count = 0;
        numTrials = 1000;
        for ( int i = 0; i < numTrials; i++) {
            result = WaTor.chooseMove( input, randGen);
            if ( result == move1) move1Count++; 
            else if ( result == move2) move2Count++;// increments count if result is correct
            else if ( result == move3) move3Count++;
            else if ( result == move4) move4Count++;
        }
        if ( move1Count != 273 || move2Count != 231 || move3Count != 234 || move4Count != 262 ) {// checks count to see it found all correct moves
            error = true;
            System.err.println("testChooseMove 4: expected 325,341,334,262 move1Count=" + move1Count + " move2Count=" + move2Count + " move3Count=" + move3Count + " move4Count=" + move4Count);
        }

        if ( error) {
            System.err.println("testChooseMove failed");
        } else {
            System.out.println("testChooseMove passed");            
        }
    }
    
    /** 
     * Checks to see if the elements of the array moves all have the value of false.
     * If any element is true, test fails with an error message printed. 
     * @param moves boolean array with true and false elements.
     */     
    private static void testClearMoves() {
        boolean error = false;
        boolean [][] moves = new boolean[4][9];
        for (int row = 0; row < moves.length; row++) {
            for (int col = 0; col < moves[row].length; col++) {
                moves[row][col] = true;//adds true values to array
            }
        }        
        WaTor.clearMoves(moves);// calls method to clear it
        for (int row = 0; row < moves.length; row++) {
            for (int col = 0; col < moves[row].length; col++) {
                if ( moves[row][col]) {// if there is still a true value = test failed
                    System.err.println("testClearMoves 0: move " + row + "," + col + " not false");
                    error = true;
                    break;
                }
            }
        }    
        if ( error) {
            System.err.println("testClearMoves failed");
        } else {
            System.out.println("testClearMoves passed");            
        }        
    }
    
    /** 
     * checks elements of the array to make sure they contain the value of Config.Empty
     * If any element does not have that value, test will fail and an error is printed.
    
     * @param moves boolean array with true and false elements. 
     */        
    private static void testEmptyArray() {
        boolean error = false;
        int [][] moves = new int[100][99];
        for (int row = 0; row < moves.length; row++) {
            for (int col = 0; col < moves[row].length; col++) {
                moves[row][col] = Config.EMPTY + 2; //make sure array is filled with not EMPTY values
            }
        }        
        WaTor.emptyArray(moves);// calls method which should empty array 
        for (int row = 0; row < moves.length; row++) {
            for (int col = 0; col < moves[row].length; col++) {
                if ( moves[row][col] != Config.EMPTY) {// if the array value is not equal to the symbol for empty then test failed
                    System.err.println("testEmptyArray 0: move " + row + "," + col + " not EMPTY");
                    error = true;
                    break;
                }
            }
        }    
        if ( error) {
            System.err.println("testEmptyArray failed");
        } else {
            System.out.println("testEmptyArray passed");            
        }            
    }
    
    /** 
     * Counts the number of non config.empty characters.
     * If there are more fish than expected then test fails with an error message printed.
     * @param fish integer array with locations of different fish.
     */ 
    private static void testCountCreatures() {
        boolean error = false;

        int[][] fish = new int[7][3];
        WaTor.emptyArray(fish);
        fish[0][0] = 1;//add fish to empty array
        fish[6][2] = 2;
        fish[0][2] = 3;
        fish[6][0] = 4;
        
        int result = WaTor.countCreatures( fish);// should return and int =4 
        if ( result != 4) {//makes sure it can properly find fish
            System.err.println("testCountCreatures 0: expected 4 found " + result );
            error = true;
        }

        if ( error) {
            System.err.println("testCountCreatures failed");
        } else {
            System.out.println("testCountCreatures passed");            
        }              
    }

}