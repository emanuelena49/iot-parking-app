package uniud.iot.lab.ui;

public interface StatusDisplay {

    /**
     * Display an "it's everything okay" status. Use this is
     * everything is working properly. (GREEN LIGHT)
     */
    void setEverythingOkayStatus();

    /**
     * Display a "something is not okay" status. Use this if,
     * for some reason, one of the components is not working properly.
     * (YELLOW LIGHT)
     */
    void setSomethingNotOkayStatus();

    /**
     * Display a "nothing is working" status. Use this in case of
     * serious problems, or simply if sensors can't be reached for
     * connection issues. (RED LIGHT)
     */
    void setNotWorkingStatus();
}
