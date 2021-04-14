package uniud.iot.lab.dataProvider.update.requester;


public class RequesterFactory {
    public static Requester getFakeDistanceRequester(String address, Integer port){
        return new FakeDistanceRequester(address,port);
    }

    public static Requester getFakeVideoRequester(String address, Integer port){

        return null;
    }
}
