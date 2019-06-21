package com.kickstart2019.A;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: KickStart
 * @description:
 * @author: Loongzee
 * @create: 2019-06-18 23:00
 */
public class Training {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        int i = 1;

        while(i <= num) {
            int N = sc.nextInt();
            int P = sc.nextInt();

            int[] skills = new int[N];
            for(int j = 0; j < N; j++) {
                skills[j] = sc.nextInt();
            }
            Arrays.sort(skills);
            int preSum = 0;
            int ans = Integer.MAX_VALUE;
            for(int j = N-1; j> N-1-P; j--){
                preSum += skills[j];
            }
            ans = Math.min(ans, skills[N-1] * P - preSum);
            for(int j = N-2; j>= P-1; j--){
                preSum = preSum - skills[j+1] + skills[j-P+1];
                ans = Math.min(ans, skills[j] * P - preSum);
            }

            System.out.println("Case #"+i+": "+ans);
            i++;
        }
    }
}
