package uniud.iot.lab.dataProvider.update.requester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.json.JSONObject;
import uniud.iot.lab.dataProvider.update.requester.exceptions.AlreadyUsedDataException;


public class FakeDistanceRequesterTest {
    private FakeDistanceRequester fakeRequester;

    @Before
    public void setUp(){
        this.fakeRequester = new FakeDistanceRequester("0.0.0.0", 1);
    }

    @Test
    public void makeRequestTest(){
        this.fakeRequester.request();
        try {
            JSONObject response = this.fakeRequester.response();
            Assert.assertNotEquals(null, response);
        } catch (AlreadyUsedDataException exp){
            Assert.fail("Exception" + exp);
        }

    }

    @Test
    public void checkTypeOfResponseTest(){
        this.fakeRequester.request();
        try {
            JSONObject response = this.fakeRequester.response();
            Assert.assertEquals(response.getClass(), JSONObject.class);
        } catch (AlreadyUsedDataException exp){
            Assert.fail("Exception" + exp);
        }

    }

    @Test(expected = AlreadyUsedDataException.class)
    public void useMultipleTimeSameResponseTest() throws Exception{
        this.fakeRequester.request();
        JSONObject firstResponse = this.fakeRequester.response();
        JSONObject secondResponse = this.fakeRequester.response();

    }



}
