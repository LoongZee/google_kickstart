package com.kickstart2019.B;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @program: google_kickstart
 * @description:
 * @author: Loongzee
 * @create: 2019-06-26 09:26
 */
public class Palindrome {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tt = sc.nextInt();

        int i = 1;
        while(i <= tt) {
            int nn = sc.nextInt();
            int qq = sc.nextInt();
            String str = sc.next();
            if(str.length() != nn) { System.err.print("error!");}
            char[] chs = str.toCharArray();
            int ans = 0;
            int[][] preSum = new int[nn+1][26];
            for(int j = 1; j <= nn; j++) {
                for(int k = 0; k < 26; k++) {
                    preSum[j][k] = preSum[j-1][k] ^ (chs[j-1] - 'A' == k ? 1 : 0);
                }
            }
            for(int j = 0; j < qq; j++) {
                int start = sc.nextInt()-1;
                int end = sc.nextInt();
                int cnt = 0;
                for(int k = 0; k < 26; k++) {
                    cnt += preSum[end][k] ^ preSum[start][k];
                }
                if(cnt == 0 || cnt == 1) {
                    ++ans;
                }
            }
            System.out.printf("Case #%d: %d\n", i, ans);
            i++;
        }
    }

}
