package ru.spbstu.telematics.java;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        final List<Integer> list = new ArrayList<Integer>();
        list.add(2);

        int threadNumber = 21; //изменение числа потоков
        int limit = 20000000; //изменение числа до которого считаем

        int last = (int)Math.sqrt(limit);
        if (last*last != limit) {
            last += 1;
        }

        final PrimeChecker firstChecker = new PrimeChecker(3,2, last, list);
        firstChecker.setName("Base checker");
        long start = System.currentTimeMillis();
        firstChecker.run();
        if(last % 2 == 0) last++;

        final PrimeChecker[] checkers = new PrimeChecker[threadNumber];
        final List[] copies = new List[threadNumber];

        for(int i=0; i<threadNumber; i++) {
            final List<Integer> listCopy = new ArrayList<Integer>(list);
            checkers[i] = new PrimeChecker(last+2*i,2*threadNumber, limit, listCopy);
            checkers[i].setName("Checker #" + i);copies[i] = listCopy;
        }

        for(int i=1; i<threadNumber; i++)
            checkers[i].start();checkers[0].run();

        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;

        try{
            for(int i=1; i<threadNumber; i++)
                checkers[i].join();
        } catch(InterruptedException ex) {}

        int total = list.size();
        for(int i=0; i<threadNumber; i++)
            total += (copies[i].size() - list.size());
        System.out.println("Total primes found: " + total);
        System.out.println("Time: " + timeConsumedMillis);
    }
}
