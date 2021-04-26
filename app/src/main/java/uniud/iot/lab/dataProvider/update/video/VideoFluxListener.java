package uniud.iot.lab.dataProvider.update.video;

public interface VideoFluxListener {

    void startListening();

    /**
     * Close the video connection
     */
    void closeConnection();

    /**
     * Check if video flux process is running
     * @return true if it is running, else false
     */
    boolean isRunning();
}
