package markd315;

import markd315.io.api.BlacklistApiController;
import markd315.io.api.UserApiController;
import org.junit.Before;

public class BaseTestBuilder {
    protected BlacklistApiController hashController;

    @Before
    public void setup(){
        hashController = new BlacklistApiController();
    }
}
