package uniud.iot.lab.dataProvider.update.requester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
            Map<String,Float> response = this.fakeRequester.response();
            Assert.assertNotEquals(null, response);
        } catch (AlreadyUsedDataException exp){
            Assert.fail("Exception" + exp);
        }

    }

    @Test
    public void checkTypeOfResponseTest(){
        this.fakeRequester.request();
        try {
            Map<String,Float> response = this.fakeRequester.response();
            Assert.assertEquals(response.getClass(), HashMap.class);
        } catch (AlreadyUsedDataException exp){
            Assert.fail("Exception" + exp);
        }

    }

    @Test(expected = AlreadyUsedDataException.class)
    public void useMultipleTimeSameResponseTest() throws Exception{
        this.fakeRequester.request();
        Map<String,Float> firstResponse = this.fakeRequester.response();
        Map<String,Float> secondResponse = this.fakeRequester.response();

    }



}
