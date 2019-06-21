package com.kickstart2018.A;

import java.util.Scanner;


public class EvenDigit {

    public static long getMinEvenDigit(long num){
        char[] chs = String.valueOf(num).toCharArray();
        int len = chs.length;
        int firstOdd = 0;
        for(char ch : chs) {
            if(((ch - '0') & 1) == 1){
                break;
            }
            ++firstOdd;
        }

        int firstEven = firstOdd;
        while(firstEven >=0){
            if(chs[firstEven] != '8' && chs[firstEven] != '9') {
                break;
            }
            --firstEven;
        }

        if(firstEven == -1) {
            return (long)(2 * Math.pow(10, len));
        }

        if(((chs[firstEven] - '0') & 1) == 1){
            chs[firstEven] += 1;
        } else {
            chs[firstEven] += 2;
        }
        for(int i = firstEven+1; i < len; i++) {
            chs[i] = '0';
        }
        return Long.valueOf(String.valueOf(chs));
    }

    public static long getMaxEvenDigit(long num){
        char[] chs = String.valueOf(num).toCharArray();
        int len = chs.length;
        int firstOdd = 0;
        for(char ch : chs) {
            if(((ch - '0') & 1) == 1){
                break;
            }
            ++firstOdd;
        }

        chs[firstOdd] -= 1;
        for(int i = firstOdd+1; i < len; i++) {
            chs[i] = '8';
        }
        return Long.valueOf(String.valueOf(chs));
    }

    public static boolean isEvenDigit(long num){
        while(num != 0) {
            if((num & 1) == 1) {
                return false;
            }
            num = num / 10;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();

        int i = 1;
        while(i <= num) {
            long number = scanner.nextLong();
            if(!isEvenDigit(number)) {
                long min = getMinEvenDigit(number);
                System.out.println(min);
                long max = getMaxEvenDigit(number);
                System.out.println(max);
                long ans = (min - number < number - max) ? min - number : number - max;
                System.out.println("Case #" + i + ": " + ans);
            } else {
                System.out.println("Case #" + i + ": " + 0);
            }
            ++i;
        }

    }
}
