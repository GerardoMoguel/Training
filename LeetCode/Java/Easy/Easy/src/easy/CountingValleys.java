/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class CountingValleys {

    /*
     * Complete the 'countingValleys' function below.
     * Gerardo Moguel
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER steps
     *  2. STRING path
     *  Link to problem: https://www.hackerrank.com/challenges/counting-valleys/problem
     */

    public static int countingValleys(int steps, String path) {
        int res=0;
        int balance=0;
        for(int i=0;i<steps;i++){
            
            if(path.charAt(i)=='D'){
                if(balance==0){
                    res++;
                }
            }
            if(path.charAt(i)=='U'){
                balance++;
            }
            else if(path.charAt(i)=='D'){
                balance--;
            }
        }
        return res;
    }

}

