package uniud.iot.lab.utils;

/**
 * An object which can be activated and deactivated.
 * It could be an UI component or an internal mechanism.
 */
public interface Activable {

    /**
     * Activate this element
     */
    void activate();

    /**
     * Deactivate this element
     */
    void deactivate();

    /**
     * check if this element is active
     * @return true if it is active
     */
    boolean isActive();
}
