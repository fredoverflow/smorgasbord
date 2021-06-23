package ieee754;

import org.junit.Test;

import static ieee754.FastPath.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FastPathTest {
    @Test
    public void minus() {
        assertEquals("-123", format(-123, Signs.MINUS, Fractionals.ZERO_ONE_TWO));
        assertEquals("0", format(0, Signs.MINUS, Fractionals.ZERO_ONE_TWO));
        assertEquals("123", format(123, Signs.MINUS, Fractionals.ZERO_ONE_TWO));
    }

    @Test
    public void minusPlus() {
        assertEquals("-123", format(-123, Signs.MINUS_PLUS, Fractionals.ZERO_ONE_TWO));
        assertEquals("+0", format(0, Signs.MINUS_PLUS, Fractionals.ZERO_ONE_TWO));
        assertEquals("+123", format(123, Signs.MINUS_PLUS, Fractionals.ZERO_ONE_TWO));
    }

    @Test
    public void minusNonePlus() {
        assertEquals("-123", format(-123, Signs.MINUS_NONE_PLUS, Fractionals.ZERO_ONE_TWO));
        assertEquals("0", format(0, Signs.MINUS_NONE_PLUS, Fractionals.ZERO_ONE_TWO));
        assertEquals("+123", format(123, Signs.MINUS_NONE_PLUS, Fractionals.ZERO_ONE_TWO));
    }

    @Test
    public void minusSpacePlus() {
        assertEquals("-123", format(-123, Signs.MINUS_SPACE_PLUS, Fractionals.ZERO_ONE_TWO));
        assertEquals(" 0", format(0, Signs.MINUS_SPACE_PLUS, Fractionals.ZERO_ONE_TWO));
        assertEquals("+123", format(123, Signs.MINUS_SPACE_PLUS, Fractionals.ZERO_ONE_TWO));
    }

    @Test
    public void two() {
        assertEquals("123,00", format(123, Signs.MINUS, Fractionals.TWO));
        assertEquals("123,40", format(123.4, Signs.MINUS, Fractionals.TWO));
        assertEquals("123,45", format(123.45, Signs.MINUS, Fractionals.TWO));
        assertEquals("123,46", format(123.456, Signs.MINUS, Fractionals.TWO));
    }

    @Test
    public void oneTwo() {
        assertEquals("123,0", format(123, Signs.MINUS, Fractionals.ONE_TWO));
        assertEquals("123,4", format(123.4, Signs.MINUS, Fractionals.ONE_TWO));
        assertEquals("123,45", format(123.45, Signs.MINUS, Fractionals.ONE_TWO));
        assertEquals("123,46", format(123.456, Signs.MINUS, Fractionals.ONE_TWO));
    }

    @Test
    public void zeroOneTwo() {
        assertEquals("123", format(123, Signs.MINUS, Fractionals.ZERO_ONE_TWO));
        assertEquals("123,4", format(123.4, Signs.MINUS, Fractionals.ZERO_ONE_TWO));
        assertEquals("123,45", format(123.45, Signs.MINUS, Fractionals.ZERO_ONE_TWO));
        assertEquals("123,46", format(123.456, Signs.MINUS, Fractionals.ZERO_ONE_TWO));
    }

    @Test
    public void worstCase() {
        assertEquals("-9223372036854774784,00", format(-9223372036854774784.0, Signs.MINUS, Fractionals.TWO));
    }

    @Test
    public void tenths() {
        String[] suffixes = {",0", ",1", ",2", ",3", ",4", ",5", ",6", ",7", ",8", ",9"};
        for (int i = 0; i < 10_000_000; ++i) {
            String s = format(i / 10.0, Signs.MINUS, Fractionals.ONE_TWO);
            assertTrue(s, s.endsWith(suffixes[i % 10]));
        }
    }

    @Test
    public void hundredths() {
        String[] suffixes = {
                ",00", ",01", ",02", ",03", ",04", ",05", ",06", ",07", ",08", ",09",
                ",10", ",11", ",12", ",13", ",14", ",15", ",16", ",17", ",18", ",19",
                ",20", ",21", ",22", ",23", ",24", ",25", ",26", ",27", ",28", ",29",
                ",30", ",31", ",32", ",33", ",34", ",35", ",36", ",37", ",38", ",39",
                ",40", ",41", ",42", ",43", ",44", ",45", ",46", ",47", ",48", ",49",
                ",50", ",51", ",52", ",53", ",54", ",55", ",56", ",57", ",58", ",59",
                ",60", ",61", ",62", ",63", ",64", ",65", ",66", ",67", ",68", ",69",
                ",70", ",71", ",72", ",73", ",74", ",75", ",76", ",77", ",78", ",79",
                ",80", ",81", ",82", ",83", ",84", ",85", ",86", ",87", ",88", ",89",
                ",90", ",91", ",92", ",93", ",94", ",95", ",96", ",97", ",98", ",99",
        };
        for (int i = 0; i < 10_000_000; ++i) {
            String s = format(i / 100.0, Signs.MINUS, Fractionals.TWO);
            assertTrue(s, s.endsWith(suffixes[i % 100]));
        }
    }

    @Test
    public void roundUp() {
        double exactlyOneEights = 0.125;
        double slightlySmaller = predecessor(exactlyOneEights);
        assertEquals("0,13", format(exactlyOneEights, Signs.MINUS, Fractionals.TWO));
        assertEquals("0,12", format(slightlySmaller, Signs.MINUS, Fractionals.TWO));
    }

    private double predecessor(double x) {
        return Double.longBitsToDouble(Double.doubleToRawLongBits(x) - 1);
    }
}
