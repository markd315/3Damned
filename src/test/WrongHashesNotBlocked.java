package test;

import static org.junit.Assert.*;

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
        String testCase = "src/main/resources/passesCase.json"; //One byte, ASCII 56.
        Assert.assertTrue(main.java.ThreeDamned.notOnBlacklist(testCase));
	}

}
