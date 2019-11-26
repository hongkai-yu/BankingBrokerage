package ui.graphic;

public class CancelException extends Exception {
    public String response() {
        return "Action cancelled";
    }
}
