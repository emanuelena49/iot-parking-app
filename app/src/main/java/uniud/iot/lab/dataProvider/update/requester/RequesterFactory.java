package uniud.iot.lab.dataProvider.update.requester;


public class RequesterFactory {
    /**
     * Returns a FakeDistanceRequester.
     * @param address Address where to make the request.
     * @param port Port where to make the request.
     * @return instance of FakeDistanceRequester.
     */
    public static Requester getFakeDistanceRequester(String address, Integer port){
        return new FakeDistanceRequester(address,port);
    }

    public static Requester getFakeVideoRequester(String address, Integer port){

        return null;
    }
}
