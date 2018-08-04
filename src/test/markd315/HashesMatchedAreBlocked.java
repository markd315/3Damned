package markd315;

import markd315.ThreeDamned;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HashesMatchedAreBlocked {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws IOException {
	    String testCase = "src/main/resources/test/rejectedCase.json"; //One byte, ASCII 56.
	    Assert.assertFalse(ThreeDamned.notOnBlacklist(testCase));
	}
}
