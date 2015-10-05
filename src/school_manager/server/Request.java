package school_manager.server;

/**
 *
 * @author abrasha
 */
public class Request {

    public enum Category {

        STUDENT, TEACHER, PARENT, USER, GROUP, SCHEDULE, UNKNOWN
    }

    public enum Method {

        GET, UPDATE, REMOVE, ADD, UNKNOWN
    }

    private final String request;
    private Category category = Category.UNKNOWN;
    private Method method = Method.UNKNOWN;
    private String params;

    public Request(String request) {

        this.request = request;
        parse(request);

    }

    public static void main(String[] args) {

        Request r = new Request("user:get?name=Andrew&group=22");
        System.out.println(r);

    }

    private void parse(String request) {

        String[] categoryField = request.split(":");
        parseCategory(categoryField[0]);
        parseMethod(categoryField[1]);

    }

    private void parseCategory(String request) {

        switch (request) {
            case "user":
                this.category = Category.USER;
                break;
            case "student":
                this.category = Category.STUDENT;
                break;
            case "parent":
                this.category = Category.PARENT;
                break;
            case "teacher":
                this.category = Category.TEACHER;
                break;
            case "group":
                this.category = Category.GROUP;
                break;
            case "schedule":
                this.category = Category.SCHEDULE;
                break;
            default:
                this.category = Category.UNKNOWN;
                break;
        }

    }

    private void parseMethod(String request) {

        String[] paramsField = request.split("\\?");

        this.params = paramsField[1];
        switch (paramsField[0]) {

            case "get":
                this.method = Method.GET;
                parseGetParams();
                break;

            case "update":
                this.method = Method.UPDATE;
                parseUpdateParams();
                break;

            case "remove":
                this.method = Method.REMOVE;
                parseRemoveParams();
                break;

            case "add":
                this.method = Method.ADD;
                parseAddParams();
                break;

            default:
                this.method = Method.UNKNOWN;
                break;

        }

    }

    private void parseGetParams(){
        
    }

    private void parseAddParams(){

    }

    private void parseUpdateParams(){

    }

    private void parseRemoveParams(){

    }

    @Override
    public String toString() {

        String result = "Request: " + this.request
                + "\nCategory: " + this.category
                + "\nMethod: " + this.method
                + "\nParams: " + this.params;

        return result;
    }

}
