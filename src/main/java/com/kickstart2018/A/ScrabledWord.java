package com.kickstart2018.A;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @program: KickStart
 * @description:
 * @author: Loongzee
 * @create: 2019-06-18 10:16
 */
public class ScrabledWord {

    static class Word{

        int[] map;
        char begin, end;


        Word(String s) {
            map = new int[26];
            char[] chs = s.toCharArray();
            begin = chs[0];
            end = chs[chs.length-1];
            for(char ch : chs){
                map[ch-'a']++;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) {
                return true;
            }
            if(obj instanceof Word) {
                Word anotherWord = (Word) obj;
                if(this.begin == anotherWord.begin && this.end == anotherWord.end) {
                    for(int i = 0; i < map.length; i++) {
                        if(this.map[i] != anotherWord.map[i]) {
                            return false;
                        }
                    }
                    return true;

                }
                return false;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 0;
            hash = 31 * hash + begin;
            hash = 31 * hash + end;
            for (int i = 0; i < map.length; i++) {
                hash = 31 * hash + map[i] * (i+1);
            }
            return hash;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        int i = 1;
        while(i <= T) {
            int L = scanner.nextInt();
            HashMap<Word, Integer> hashmap = new HashMap<Word, Integer>();
            HashSet<Integer> lens = new HashSet<Integer>();

            for(int j = 0 ; j < L; j++) {
                String s = scanner.next();
                Word w = new Word(s);
                if(hashmap.containsKey(w)) {
                    hashmap.put(w, hashmap.get(w) + 1);
                } else {
                    hashmap.put(w, 1);
                }
                lens.add(s.length());
            }

            long[] params = new long[7];
            params[0] = scanner.next().charAt(0);
            params[1] = scanner.next().charAt(0);
            for(int j = 2; j < params.length; j++) {
                params[j] = scanner.nextInt();
            }
            String str = generateStr(params);
            int strlen = str.length();
            int ans = 0;
            for(int l : lens) {
                if(strlen < l) continue;

                String s = str.substring(0,l);
                Word w = new Word(s);
                int k = 0;
                while(true) {
                    if(hashmap.containsKey(w)) {
                        ans += hashmap.get(w);
                        hashmap.remove(w);
                    }
                    if(k+l >= strlen) {break;}
                    w.map[str.charAt(k) - 'a']--;
                    w.map[str.charAt(k+l) - 'a']++;
                    k++;
                    w.begin = str.charAt(k);
                    w.end = str.charAt(k + l - 1);
                }

            }

            System.out.println("Case #" + i + ": " + ans);
            i++;
        }
    }

    private static String generateStr(long[] params) {
        long len = params[2];
        StringBuilder sb = new StringBuilder();
        long[] x = new long[2];
        for(int i = 0; i < len; i++) {
            if(i == 0 || i == 1) {
                sb.append((char)(params[i]));
                x[i] = params[i];
            } else {
                long tmp = (params[3] * x[1] + params[4] * x[0] + params[5]) % params[6];
                char ch = (char)(tmp % 26 + 97);
                sb.append(ch);
                x[0] = x[1];
                x[1] = tmp;
            }
        }
        return sb.toString();
    }
}
