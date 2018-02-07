package com.example.josh.assignment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Created by josh on 2/7/18.
 */

public class ScoreTable {
    private ArrayList<String> scores;
    private ArrayList<String> timestamps;

    public ScoreTable(String entry){
        String[] score_pair = entry.split("\n");

        for(int i = 0; i < score_pair.length; i++){
            String[] pair_split = score_pair[i].split("\t");
            //timestamps.add();
        }

    }
}
