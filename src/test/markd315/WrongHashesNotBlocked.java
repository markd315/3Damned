package markd315;

import markd315.ThreeDamned;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class WrongHashesNotBlocked {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws IOException {
        String testCase = "src/main/resources/test/passesCase.json"; //One byte, ASCII 56.
        Assert.assertTrue(ThreeDamned.notOnBlacklist(testCase));
	}

}
