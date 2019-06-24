package com.kickstart2019.A;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: google_kickstart
 * @description:
 * @author: Loongzee
 * @create: 2019-06-22 15:41
 */
public class Contention {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tt = sc.nextInt();

        int i = 1;
        while(i <= tt) {
            int nn = sc.nextInt();
            int qq = sc.nextInt();
            Booking[] bookings = new Booking[qq];
            for(int j = 0; j < qq; j++) {
                bookings[j] = new Booking(sc.nextInt(),sc.nextInt());
            }
            Arrays.sort(bookings);
            int ans = nn;

            int[] isBooking = new int[nn+1];
            for(int j = 0; j < qq; j++) {
                int seats = 0;
                for(int k = bookings[j].start; k <= bookings[j].end; k++) {
                    if(isBooking[k] == 0) {
                        seats++;
                        isBooking[k] = 1;
                    }
                }
                if(seats == 0) { ans = 0; break;}
                else { ans = Math.min(ans, seats);}
            }

            System.out.printf("Case #%d: %d\n", i, ans);
            i++;
        }

    }
}

class Booking implements Comparable<Booking>{

    int start;
    int end;
    int gap;

    Booking(int start, int end) {
        this.start = start;
        this.end = end;
        this.gap = end - start;
    }

    @Override
    public int compareTo(Booking o) {
        return Integer.compare(this.gap, o.gap);
    }
}
