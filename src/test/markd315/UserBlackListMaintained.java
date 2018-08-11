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


    @Test
    public void rejectedDueToUser() throws IOException {
        File testFile = new File("src/main/resources/test/passesCase.json"); //One byte, ASCII 53.
        Assert.assertFalse(hashController.fileNotOnBlacklist(testFile, "marvinsroom").getBody().notBlocked());
    }

    @Test
    public void userRejectedAfterViolation() throws IOException {
        //Try before getting the user banned
        File testFile = new File("src/main/resources/test/passesCase.json"); //One byte, ASCII 56.
        Assert.assertTrue(hashController.fileNotOnBlacklist(testFile, "getbummedout").getBody().notBlocked());
        //Get the user banned
        testFile = new File("src/main/resources/test/rejectedCase.json"); //One byte, ASCII 56.
        Assert.assertFalse(hashController.fileNotOnBlacklist(testFile, "getbummedout").getBody().notBlocked());
        //Try with the ban in place
        testFile = new File("src/main/resources/test/passesCase.json"); //One byte, ASCII 53.
        Assert.assertFalse(hashController.fileNotOnBlacklist(testFile, "getbummedout").getBody().notBlocked());
    }

    @Test
    public void canAddToBlacklist() throws IOException {
        //Try before getting the user banned
        File testFile = new File("src/main/resources/test/passesCase.json"); //One byte, ASCII 56.
        Assert.assertTrue(hashController.fileNotOnBlacklist(testFile, "ministryofalienation").getBody().notBlocked());
        //Manually ban the passesCase file.
        hashController.addFile(new File("src/main/resources/test/passesCase.json"));
        //Try with the ban in place
        Assert.assertFalse(hashController.fileNotOnBlacklist(testFile, "ministryofalienation").getBody().notBlocked());
    }

    @Test
    public void canUnban() throws IOException {
        Assert.assertFalse(hashController.getUserController().isNotBanned("marvinsroom").getBody().notBlocked());
        hashController.getUserController().unban("marvinsroom");
        Assert.assertTrue(hashController.getUserController().isNotBanned("marvinsroom").getBody().notBlocked());
    }
}
