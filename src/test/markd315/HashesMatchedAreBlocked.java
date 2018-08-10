package markd315;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class HashesMatchedAreBlocked extends BaseTestBuilder{

	@After
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws IOException {
	    String testCase = "src/main/resources/test/rejectedCase.json"; //One byte, ASCII 56.
	    Assert.assertFalse(hashController.notOnBlacklist(testCase));
	}
}
