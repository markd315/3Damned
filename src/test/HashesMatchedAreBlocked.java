package test;

import static org.junit.Assert.*;

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
	    String testCase = "src/main/resources/rejectedCase.json"; //One byte, ASCII 56.
	    Assert.assertFalse(main.java.ThreeDamned.notOnBlacklist(testCase));
	}

}
