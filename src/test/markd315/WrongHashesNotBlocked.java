package markd315;

import org.junit.*;

import java.io.File;
import java.io.IOException;

public class WrongHashesNotBlocked extends BaseTestBuilder{

	@Test
	public void test() throws IOException {
        File testCase = new File("src/main/resources/test/passesCase.json"); //One byte, ASCII 56.
        Assert.assertTrue(hashController.fileNotOnBlacklist(testCase, null).getBody().notBlocked());
	}

}
