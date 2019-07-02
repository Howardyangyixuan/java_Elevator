package elevator;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElevatorTest {

    @Test(expected = Throwable.class)
    public void testElevator()throws Throwable{
        Query q1 = new Query("(QR,1,UP,1)");
        assertFalse(q1.repOk());
        System.out.println("here");
    }
}
