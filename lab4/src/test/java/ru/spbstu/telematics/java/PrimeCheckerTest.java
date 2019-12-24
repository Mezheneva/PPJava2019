package ru.spbstu.telematics.java;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class PrimeCheckerTest {
    private List<Integer> list;
    private int threadNumber;
    private int limit;
    private int last;

    @Before
    public void setUp() throws Exception {
        list = new ArrayList<Integer>();
        list.add(2);

        threadNumber = 21; //изменение числа потоков
        limit = 1000; //изменение числа до которого считаем

        last = (int)Math.sqrt(limit);
        if (last*last != limit) {
            last += 1;
        }

        final PrimeChecker firstChecker = new PrimeChecker(3,2, last, list);
        firstChecker.setName("Base checker");
        firstChecker.run();
        if(last % 2 == 0) last++;

    }

    @Test
    public void test1000() {
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

        try{
            for(int i=1; i<threadNumber; i++)
                checkers[i].join();
        } catch(InterruptedException ex) {}

        int total = list.size();
        for(int i=0; i<threadNumber; i++)
            total += (copies[i].size() - list.size());
        System.out.println("Total primes found: " + total);
        assertThat(total, is(168));
    }

}
