package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

public class HashesMatchedAreBlocked {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
	    byte[] testcase = new byte[1];
	    testcase[0] = 12;
	    Assert.assertFalse(main.java.ThreeDamned.notOnBlacklist(testcase.));

	}

}
