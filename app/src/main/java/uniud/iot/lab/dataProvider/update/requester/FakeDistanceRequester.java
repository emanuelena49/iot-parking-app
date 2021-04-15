package uniud.iot.lab.dataProvider.update.requester;
import org.json.JSONException;
import org.json.JSONObject;

import uniud.iot.lab.dataProvider.update.requester.exceptions.AlreadyUsedDataException;


public class FakeDistanceRequester implements Requester{
    private String address = null;
    private Integer port = null;
    private JSONObject data = null;
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


    public JSONObject response() throws AlreadyUsedDataException {
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
                try {
                    this.data = new JSONObject("{1:1}");
                }catch (JSONException err){
                    this.data = new JSONObject();
                }
                break;
            case 2:
                try {
                    this.data = new JSONObject("{1:2}");
                }catch (JSONException err){
                    this.data = new JSONObject();
                }
                break;
            case 3:
                try {
                    this.data = new JSONObject("{1:3}");
                }catch (JSONException err){
                    this.data = new JSONObject();
                }
                break;
        }
    }


}


