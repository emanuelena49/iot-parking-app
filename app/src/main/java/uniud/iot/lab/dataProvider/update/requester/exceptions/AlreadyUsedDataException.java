package uniud.iot.lab.dataProvider.update.requester.exceptions;

public class AlreadyUsedDataException extends Exception{
    public AlreadyUsedDataException(){
        super("Data already used. Make a new request before response.");
    }

    public AlreadyUsedDataException(String message){
        super(message);
    }
}
