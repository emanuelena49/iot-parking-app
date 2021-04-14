package uniud.iot.lab.dataProvider.update.updater;

public interface Updater {
    public void run();
    public void stop();
    public boolean isRunning();

}
