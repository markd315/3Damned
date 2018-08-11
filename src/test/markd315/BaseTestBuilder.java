package markd315;

import markd315.io.api.BlacklistApiController;
import markd315.io.api.UserApiController;
import org.junit.After;
import org.junit.Before;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BaseTestBuilder {
    protected BlacklistApiController hashController;

    @Before
    public void setup(){
        hashController = new BlacklistApiController();
    }

    @After
    public void tearDownAfterClass() throws Exception {
        //Undo any bans in test
        Path from = Paths.get("src/main/resources/test/userTestBlacklist_bk.json"); //convert from File to Path
        Path to = Paths.get("src/main/resources/test/userTestBlacklist.json"); //convert from String to Path
        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        //Ensure that '5' is not banned
        from = Paths.get("src/main/resources/test/hashTestBlacklist_bk.json"); //convert from File to Path
        to = Paths.get("src/main/resources/test/hashTestBlacklist.json"); //convert from String to Path
        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
    }
}
