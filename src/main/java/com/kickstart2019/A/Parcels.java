package com.kickstart2019.A;

import java.util.*;

/**
 * @program: KickStart
 * @description:
 * @author: Loongzee
 * @create: 2019-06-21 17:36
 */
public class Parcels {

    static int[][] offest = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    public static int bfs(int row, int col, int[][] data, int[][] dis) {

        LinkedList<Pair> queue = new LinkedList<Pair>();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(data[i][j] == 1) {
                    dis[i][j] = 0;
                    queue.offer(new Pair(i,j));
                }
            }
        }

        int maxdis = 0;
        while(!queue.isEmpty()) {
            Pair pos = queue.poll();
            for(int i = 0; i < 4; i++) {
                int x = pos.x + offest[i][0];
                int y = pos.y + offest[i][1];
                if(x<0 || y<0 || x>=row || y>=col) {continue;}
                if(dis[x][y] != -1) {continue;}
                maxdis = dis[x][y] = dis[pos.x][pos.y] + 1;
                queue.offer(new Pair(x,y));
            }
        }

        return maxdis;
    }

    public static boolean canReached(int col, int row, int d, int[][] dis) {
        int xmin = Integer.MAX_VALUE;
        int ymin = Integer.MAX_VALUE;
        int xmax = Integer.MIN_VALUE;
        int ymax = Integer.MIN_VALUE;
        boolean flag = false;
        for(int i = 0; i < col; i++){
            for(int j = 0; j< row; j++) {
                if(dis[i][j] > d) {
                    xmin = Math.min(xmin, i+j);
                    ymin = Math.min(ymin, i-j);
                    xmax = Math.max(xmax, i+j);
                    ymax = Math.max(ymax, i-j);
                    flag = true;
                }
            }
        }
        if(!flag) return true;
        for(int i = 0 ; i < col; i++) {
            for(int j = 0 ; j < row; j++) {
                if(Math.abs(xmin-(i+j)) <=d &&
                        Math.abs(ymin-(i-j)) <=d &&
                        Math.abs(xmax-(i+j)) <=d &&
                        Math.abs(ymax-(i-j)) <=d){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tt = sc.nextInt();
        int i = 1;
        while(i <= tt) {
            int row = sc.nextInt();
            int col = sc.nextInt();

            int[][] data = new int[row][col];
            int[][] dis = new int[row][col];
            for(int j = 0; j < row; j++)
                Arrays.fill(dis[j], -1);

            for(int j = 0; j < row; j++) {
                String s = sc.next();
                for(int k = 0; k < col; k++) {
                    data[j][k] = s.charAt(k) - '0';
                }
            }

            int high = bfs(row, col, data, dis);
            int low = 0;
            while(low <= high) {
                int mid = (low + high) >>> 1;
                if(canReached(row,col,mid,dis)) {
                    high = mid - 1;
                }else {
                    low = mid + 1;
                }
            }

            System.out.printf("Case #%d: %d\n", i, high + 1);
            i++;
        }

    }
}

class Pair{

    int x;
    int y;

    Pair(int x ,int y){
        this.x = x;
        this.y = y;
    }
}
