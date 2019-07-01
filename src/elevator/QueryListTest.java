package elevator;
import static org.junit.Assert.*;
import org.junit.Test;

public class QueryListTest {
    @Test
    public void testQueryList1() {
        try {
            QueryList queryList = new QueryList(10, 1);
            Query a1 = new Query("(FR,1,UP,1)");
            Query a2 = new Query("(FR,5,UP,2)");
            Query a3 = new Query("(FR,1,UP,3)");
            Query a4 = new Query("(FR,4,UP,4)");
            queryList.append(a1);
            queryList.append(a2);
            queryList.append(a3);
            queryList.append(a4);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Test(expected = Exception.class)
    public void testQueryList2() throws Throwable {
        //try{
        QueryList queryList = new QueryList(10, 1);
        Query a1 = new Query("(FR,11,UP,1)");
        queryList.append(a1);
        //} catch(Throwable e) {
        //   e.printStackTrace();
    }

    @Test(expected = UnsortedException.class)
    public void testQueryList3() throws Throwable {
        QueryList queryList = new QueryList(10, 1);
        Query a1 = new Query("(FR,1,UP,3)");
        Query a2 = new Query("(FR,5,UP,2)");
        Query a3 = new Query("(FR,6,UP,3)");
        Query a4 = new Query("(FR,7,UP,4)");
        queryList.append(a1);
        queryList.append(a2);
        queryList.append(a3);
        queryList.append(a4);
    }
}