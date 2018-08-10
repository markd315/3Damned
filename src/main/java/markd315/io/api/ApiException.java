package markd315.io.api;

@javax.annotation.Generated(value = "io.io.codegen.languages.SpringCodegen", date = "2018-08-04T19:33:03.042-04:00")

public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
