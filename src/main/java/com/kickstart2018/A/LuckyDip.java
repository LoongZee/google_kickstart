package com.kickstart2018.A;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: KickStart
 * @description:
 * @author: Loongzee
 * @create: 2019-06-17 10:08
 */
public class LuckyDip {

    public static double getExpectedVal(int N, int K, int[] inArray, long[] preComputeSum) {
        double ave = 0.0;
        if(K == 0) {
            ave = preComputeSum[preComputeSum.length-1];
        } else {

            double expected = getExpectedVal(N, K - 1, inArray, preComputeSum);

            int index = binarySearch(inArray, expected);
            if(index == 0) ave = preComputeSum[preComputeSum.length-1];
            else ave =  preComputeSum[preComputeSum.length-1] - preComputeSum[index-1] + expected * index;
        }

        ave /= N;
        return ave;
    }

    public static int binarySearch(int[] inArray, double expected) {

        int low = 0;
        int high = inArray.length-1;
        int mid = (low + high) >>> 1;
        while(low <= high) {
            mid = (low + high) >>> 1;
            if(inArray[mid] <= expected) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        if(inArray[mid] <= expected) {
            mid++;
        }

        return mid;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.valueOf(scanner.nextLine());
        int i = 1;
        while(i <= num) {
            String[] line1 = scanner.nextLine().split(" ");
            int N = Integer.valueOf(line1[0]);
            int K = Integer.valueOf(line1[1]);

            String[] line2 = scanner.nextLine().split(" ");
            int[] inArray = new int[N];
            for(int j = 0; j < N; j++) {
                inArray[j] = Integer.valueOf(line2[j]);
            }
            Arrays.sort(inArray);

            long[] preComputeSum = new long[N];
            for(int j = 0; j < N; j++){
                if(j == 0) preComputeSum[j] = inArray[j];
                else preComputeSum[j] = preComputeSum[j-1] + inArray[j];
            }

            double ans = getExpectedVal(N, K, inArray, preComputeSum);
            System.out.println("Case #" + i + ": " + ans);
            i++;
        }

    }
}


//import java.util.Scanner;
//
///**
// * @program: KickStart
// * @description:
// * @author: Loongzee
// * @create: 2019-06-17 10:08
// */
//public class Solution {
//
//    public static double getExpectedVal(int N, int K, int[] inArray) {
//        double ave = 0.0;
//        if(K == 0) {
//            for(int i = 0; i < N; i++) {
//                ave += inArray[i];
//            }
//        } else {
//            double expected = getExpectedVal(N, K - 1, inArray);
//            for (int i = 0; i < N; i++) {
//                ave += (inArray[i] > expected ? inArray[i] : expected);
//            }
//        }
//
//        ave /= N;
//        return ave;
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int num = Integer.valueOf(scanner.nextLine());
//        int i = 1;
//        while(i <= num) {
//            String[] line1 = scanner.nextLine().split(" ");
//            int N = Integer.valueOf(line1[0]);
//            int K = Integer.valueOf(line1[1]);
//
//            String[] line2 = scanner.nextLine().split(" ");
//            int[] inArray = new int[N];
//            for(int j = 0; j < N; j++) {
//                inArray[j] = Integer.valueOf(line2[j]);
//            }
//
//            double ans = getExpectedVal(N, K, inArray);
//            System.out.println("Case #" + i + ": " + ans);
//            i++;
//        }
//
//    }
//}
