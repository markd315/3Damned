package markd315.io.api;

@javax.annotation.Generated(value = "io.io.codegen.languages.SpringCodegen", date = "2018-08-04T19:33:03.042-04:00")

public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
