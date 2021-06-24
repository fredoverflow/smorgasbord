package bank;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IbanTest {
    @Test
    public void wikipedia1() {
        assertEquals("DE07123412341234123412", Iban.de("1234123412", "12341234"));
    }

    @Test
    public void wikipedia2() {
        assertEquals("DE68210501700012345678", Iban.de("0012345678", "21050170"));
    }

    @Test
    public void wikipedia3() {
        assertEquals("DE65200411330830330700", Iban.de("0830330700", "20041133"));
    }
}
