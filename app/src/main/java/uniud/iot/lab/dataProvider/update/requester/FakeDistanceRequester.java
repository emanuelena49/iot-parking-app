package uniud.iot.lab.dataProvider.update.requester;
import org.json.JSONObject;


public class FakeDistanceRequester implements Requester{
    private String address = null;
    private Integer port = null;
    private JSONObject data = null;


    public FakeDistanceRequester(String address, Integer port){
        this.address = address;
        this.port = port;
    }

    @Override
    public void request(){
    }


    public JSONObject response(){
        return this.data;
    }


}


