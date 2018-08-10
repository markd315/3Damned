package markd315;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class HashesMatchedAreBlocked extends BaseTestBuilder{

	@After
	public void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws IOException {
	    File testCase = new File("src/main/resources/test/rejectedCase.json"); //One byte, ASCII 56.
	    Assert.assertFalse(hashController.fileNotOnBlacklist(testCase, null).getBody().notBlocked());
	}
}
