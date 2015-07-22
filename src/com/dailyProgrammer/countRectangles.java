package com.dailyProgrammer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Takens an input from a file and counts the number of four sided figures in it.
 * Based off of this challenge:
 * https://www.reddit.com/r/dailyprogrammer/comments/3e5b0o/20150722_challenge_224_intermediate_detecting/
 * ****
 * Currently unfinished. Some type of problem with accessing the list.
 * ****
 * Created by Corey on 7/22/2015.
 */
public class countRectangles {

    // A list of strings, making the map of rectangles
    private List<String> l;
    // The curX position of the cursor
    private int curX;
    // The curY position of the cursor
    private int curY;
    // Width
    private int w;
    // Height
    private int h;

    // These are for checking where our original + was
    private int origX;
    private int origY;

    public countRectangles() {
        l = new ArrayList<>();
        getInput(l);
        curX = 0;
        curY = 0;
        w = l.get(0).length();
        h = l.size();
    }

    /**
     * Gets the input from the /resources/rectangles.txt file
     * @param s an empty list
     * @post s contains a list of the lines in the file
     */
    protected void getInput(List<String> s) {
        // Open the file
        File file = new File("D:\\Programming\\DailyProgrammer\\resources\\rectangles.txt");
        Scanner inputFile = null;

        // Connect the scanner to input file
        try {
            inputFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        //Read the scanner and put it into s
        while(inputFile.hasNext()) {
            s.add(inputFile.nextLine());
        }
    }

    /**
     * @return the character the current cursor points to
     */
    protected char item() {
        // Checking if we have gone out of bounds
        if(curX < 0 || curX >= w || curY < 0 || curY >= h) {
            return ' ';
        }
        return l.get(curY).charAt(curX);
    }

    /**
     * Moves the cursor one spot right
     */
    protected void moveRight() {
        curX++;
    }

    /**
     * Moves the cursor one spot down
     */
    protected void moveDown() {
        curY++;
    }

    /**
     * Moves the cursor one spot left
     */
    protected void moveLeft() {
        curX--;
    }

    /**
     * Moves the cursor one spot up
     */
    protected void moveUp() {
        curY--;
    }

    /**
     * Moves the cursor to a specified location
     * @param newX new curX coordinate
     * @param newY new curY coordinate
     */
    protected void move(int newX, int newY) {
        curX = newX;
        curY = newY;
    }

    /**
     * This counts the number of rectangle in a given ASCII input
     * Assumes there are not margins on the top of the map.
     * @return the number of rectangles in l
     */
    protected int count() {
        int result = 0;
        for(int i = 0; i < w; i++) {
            for(int j = 0; j < h; j++) {
                if( l.get(j).charAt(i) == '+' ) {
                    origX = i;
                    origY = j;
                    result += check(i, j);
                }
            }
        }

        return result;
    }

    protected int check(int x, int y) {
        move(x, y);
        moveRight();
        return helperRight(x, y);
    }

    protected int helperRight(int x, int y) {
        int result = 0;
        int inc = 1;

        while( item() == '-' || item() == '+' ) {
            if( item() == '+' ) {
                moveDown();
                result += helperDown(x+inc, y);
            }
            moveRight();
            inc++;
        }

        move(x, y);
        return result;
    }

    protected int helperDown(int x, int y) {
        int result = 0;
        int inc = 1;

        while( item() == '|' || item() == '+' ) {
            if( item() == '+' ) {
                moveLeft();
                result += helperLeft(x, y+inc);
            }
            moveDown();
            inc++;
        }

        move(x, y);
        return result;
    }

    protected int helperLeft(int x, int y) {
        int result = 0;
        int inc = 1;

        while( item() == '-' || item() == '+' ) {
            if( item() == '+' ) {
                moveUp();
                result += helperUp(x - inc, y);
            }
            moveLeft();
            inc++;
        }

        move(x, y);
        return result;
    }

    protected int helperUp(int x, int y) {

        while( item() == '|' || item() == '+' ) {
            if( (curX == origX) && (curY == origY) ) {
                move(x, y);
                return 1;
            }
            moveUp();
        }

        move(x,y);
        return 0;
    }

    public static void main(String[] args) {
        countRectangles r = new countRectangles();

        System.out.println("There are " + r.count() + " rectangles.");
    }
}
