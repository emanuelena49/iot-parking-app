package uniud.iot.lab.dataProvider.update.requester;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import uniud.iot.lab.dataProvider.update.requester.exceptions.AlreadyUsedDataException;


public class FakeDistanceRequester implements DistanceRequester{
    private String address = null;
    private Integer port = null;
    private Map<String, Float> data = null;
    private boolean freshData = false;


    public FakeDistanceRequester(String address, Integer port){
        this.address = address;
        this.port = port;
    }

    @Override
    public void request(){
        changeData();
        this.freshData = true;

    }


    public Map<String, Float> response() throws AlreadyUsedDataException {
        if (this.freshData) {
            this.freshData = false;
            return this.data;
        }else{
            throw new AlreadyUsedDataException();
        }

    }

    private void changeData(){
        int min = 1;
        int max = 3;

        int random_value = (int)Math.floor(Math.random()*(max-min+1)+min);
        switch (random_value){
            case 1:
                HashMap<String,Float> data1 = new HashMap<String,Float>();
                data1.put("1",(float) 1);
                this.data = data1;
                break;
            case 2:
                HashMap<String,Float> data2 = new HashMap<String,Float>();
                data2.put("1",(float) 2);
                this.data = data2;
                break;
            case 3:
                HashMap<String,Float> data3 = new HashMap<String,Float>();
                data3.put("1",(float) 3);
                this.data = data3;
                break;
        }
    }


}


