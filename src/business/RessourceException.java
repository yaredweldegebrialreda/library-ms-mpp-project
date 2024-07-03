package business;

public class RessourceException extends Exception{

    public RessourceException(String resource, String id, String message) {
        super(buildMessage(resource, id, message));
    }
    private static String buildMessage(String resource, String id, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(resource).append(" of id ").append(id).append(message);
        return sb.toString();
    }
}
