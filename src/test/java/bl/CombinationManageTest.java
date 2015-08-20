package bl;

import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import option.MatlabOption;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Nifury on 8/19/2015.
 */
public class CombinationManageTest {

    private static MatlabOption option;
    private static MWNumericArray s, k, t, r, sg, smin, smax, t_end, t_now, j;

    private static void printAndDispose(Object[] obj) {
        System.out.println("--------------------------");
        for (Object o : obj) {
            System.out.println(o);
        }
        System.out.println("--------------------------");
        System.out.println();
        MWArray.disposeArray(obj);
    }

    @BeforeClass
    public static void beforeClass() throws MWException {
        s = new MWNumericArray(3886.14, MWClassID.DOUBLE);
        k = new MWNumericArray(10000, MWClassID.DOUBLE);
        t = new MWNumericArray(300, MWClassID.DOUBLE);
        r = new MWNumericArray(0.5, MWClassID.DOUBLE);
        sg = new MWNumericArray(0.5, MWClassID.DOUBLE);
        smin = new MWNumericArray(100, MWClassID.DOUBLE);
        smax = new MWNumericArray(200, MWClassID.DOUBLE);
        t_end = new MWNumericArray(1, MWClassID.DOUBLE);
        t_now = new MWNumericArray(0.4, MWClassID.DOUBLE);
        j = new MWNumericArray(3000, MWClassID.DOUBLE);
        option = new MatlabOption();
    }

    @AfterClass
    public static void afterClass() {
        MWArray.disposeArray(s);
        MWArray.disposeArray(k);
        MWArray.disposeArray(t);
        MWArray.disposeArray(r);
        MWArray.disposeArray(sg);
        MWArray.disposeArray(smin);
        MWArray.disposeArray(smax);
        MWArray.disposeArray(t_end);
        MWArray.disposeArray(t_now);
        MWArray.disposeArray(j);
        option.dispose();
    }

    @Test
    public void testAverageStrike() throws MWException {
        printAndDispose(option.AverageStrikeCall(5, s, r, sg, j, t_now, t_end));
        printAndDispose(option.AverageStrikePut(5, s, r, sg, j, t_now, t_end));
    }

    @Test
    public void testAveragePrice() throws MWException {
        printAndDispose(option.AveragePriceCall(5, s, t, r, sg, k));
        printAndDispose(option.AveragePricePut(5, s, t, r, sg, k));
    }

    @Test
    public void testBinaryAON() throws MWException {
        printAndDispose(option.BinaryAONCall(5, s, t, r, sg, k));
        printAndDispose(option.BinaryAONPut(5, s, t, r, sg, k));
    }

    @Test
    public void testBinaryCON() throws MWException {
        printAndDispose(option.BinaryCONCall(5, s, t, r, sg, k));
        printAndDispose(option.BinaryCONPut(5, s, t, r, sg, k));
    }

    @Test
    public void testLookBackFixed() throws MWException {
        printAndDispose(option.lookbackfixedcall(5, s, k, smax, t, r, sg));
        printAndDispose(option.lookbackfixedput(5, s, k, smin, t, r, sg));
    }

    @Test
    public void testLookBackFloat() throws MWException {
        printAndDispose(option.lookbackfloatcall(5, s, smin, t, r, sg));
        printAndDispose(option.lookbackfloatput(5, s, smax, t, r, sg));
    }

    @Test
    public void testVanilla() throws MWException {
        printAndDispose(option.vanillacall(5, s, k, t, r, sg));
        printAndDispose(option.vanillaput(5, s, k, t, r, sg));
    }

}