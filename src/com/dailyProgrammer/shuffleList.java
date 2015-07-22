package com.dailyProgrammer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

/**
 * Takes a list of values in order, and it must be shuffled.
 * Sequential runs will yield different results.
 * Created by Corey on 7/21/2015.
 */
public class shuffleList<I> {

    public shuffleList() {
        run();
    }

    /**
     * This is what does the actual work.
     * @param l a list to be shuffled
     */
    public void shuffle(List<I> l) {
        I item;
        int index;

        List<I> temp = new ArrayList<>();

        while(!l.isEmpty()) {
            index = (int) (Math.random() * l.size());
            item = l.get(index);
            l.remove(index);
            temp.add(item);
        }
        l.addAll(temp);
    }

    /**
     * Fills a list with values from the user
     * @param l the list to be filled
     */
    private void getInput(List l) {
        String in = null;
        String[] values;

        System.out.println("Give a space separated list of sorted values, and then press enter.");
        System.out.println("For example, \"1 2 3 4 5 6\"");

        // Try to get a line of input in
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            in = reader.readLine();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        // Parse the input and put it onto the list
        assert in != null;
        in = in.trim();
        values = in.split(" ");
        Collections.addAll(l, values);
    }

    /**
     * Reads to output a list
     * @param i the list to be printed
     */
    private void putOutput(List i) {
        System.out.println(i.toString());
    }

    /**
     * Handles function calls.
     */
    private void run() {
        List<I> l = new ArrayList<I>();
        getInput(l);
        shuffle(l);
        putOutput(l);
    }

    public static void main(String[] args) {
        new shuffleList();
    }
}