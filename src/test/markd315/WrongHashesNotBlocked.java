package markd315;

import org.junit.*;

import java.io.IOException;

public class WrongHashesNotBlocked extends BaseTestBuilder{

	@After
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws IOException {
        String testCase = "src/main/resources/test/passesCase.json"; //One byte, ASCII 56.
        Assert.assertTrue(hashController.notOnBlacklist(testCase));
	}

}
