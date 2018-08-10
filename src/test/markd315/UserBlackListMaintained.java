package markd315;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UserBlackListMaintained extends BaseTestBuilder {
    @After
    public static void tearDownAfterClass() throws Exception {
        //Undo any bans in test
        Path from = Paths.get("src/main/resources/test/userTestBlacklist_bk.json"); //convert from File to Path
        Path to = Paths.get("src/main/resources/test/userTestBlacklist.json"); //convert from String to Path
        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        //Ensure that '5' is not banned
        from = Paths.get("src/main/resources/test/hashTestBlacklist_bk.json"); //convert from File to Path
        to = Paths.get("src/main/resources/test/hashTestBlacklist.json"); //convert from String to Path
        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    public void rejectedDueToUser() throws IOException {
        String testFile = "src/main/resources/test/passesCase.json"; //One byte, ASCII 53.
        Assert.assertFalse(hashController.notOnBlacklist(testFile, "marvinsroom"));
    }

    @Test
    public void userRejectedAfterViolation() throws IOException {
        //Try before getting the user banned
        String testFile = "src/main/resources/test/passesCase.json"; //One byte, ASCII 56.
        Assert.assertTrue(hashController.notOnBlacklist(testFile, "getbummedout"));
        //Get the user banned
        testFile = "src/main/resources/test/rejectedCase.json"; //One byte, ASCII 56.
        Assert.assertFalse(hashController.notOnBlacklist(testFile, "getbummedout"));
        //Try with the ban in place
        testFile = "src/main/resources/test/passesCase.json"; //One byte, ASCII 53.
        Assert.assertFalse(hashController.notOnBlacklist(testFile, "getbummedout"));
    }

    @Test
    public void canAddToBlacklist() throws IOException {
        //Try before getting the user banned
        String testFile = "src/main/resources/test/passesCase.json"; //One byte, ASCII 56.
        Assert.assertTrue(hashController.notOnBlacklist(testFile, "ministryofalienation"));
        //Manually ban the passesCase file.
        hashController.addFile(new File("src/main/resources/test/passesCase.json"));
        //Try with the ban in place
        Assert.assertFalse(hashController.notOnBlacklist(testFile, "ministryofalienation"));
    }
}
