package plus.cove.jazzy.api.test.codewars;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FoundationCode {
    private static void testing(int actual, int expected) {
        assertEquals(expected, actual);
    }

    public static int nbYear(int p0, double percent, int aug, int p) {
        if (p <= p0) {
            return 0;
        }

        double pDou = percent / 100;
        int pNow = p0;
        int year = 0;
        while (p >= pNow) {
            pNow = pNow + (int) (pNow * pDou) + aug;
            year++;
        }
        return year;
    }

    @Test
    public void nbYearTest() {
        System.out.println("Fixed Tests: nbYear");
        testing(FoundationCode.nbYear(1500, 5, 100, 5000), 15);
        testing(FoundationCode.nbYear(1500000, 2.5, 10000, 2000000), 10);
        testing(FoundationCode.nbYear(1500000, 0.25, 1000, 2000000), 94);
    }

    public static int findIt(int[] a) {
        int odd = 0;
        for(int i=0; i < a.length; i++){
            int times = 0;
            for(int j=0; j < a.length; j++){
                if(a[i] == a[j]){
                    times++;
                }
            }
            if(times % 2 == 1){
                odd = a[i];
                break;
            }
        }
        return odd;
    }

    @Test
    public void findItTest() {
        assertEquals(5, FoundationCode.findIt(new int[]{20,1,-1,2,-2,3,3,5,5,1,2,4,20,4,-1,-2,5}));
        assertEquals(-1, FoundationCode.findIt(new int[]{1,1,2,-2,5,2,4,4,-1,-2,5}));
        assertEquals(5, FoundationCode.findIt(new int[]{20,1,1,2,2,3,3,5,5,4,20,4,5}));
        assertEquals(10, FoundationCode.findIt(new int[]{10}));
        assertEquals(10, FoundationCode.findIt(new int[]{1,1,1,1,1,1,10,1,1,1,1}));
        assertEquals(1, FoundationCode.findIt(new int[]{5,4,3,2,1,5,4,3,2,10,10}));
    }
}
