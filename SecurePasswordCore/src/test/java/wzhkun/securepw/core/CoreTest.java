package wzhkun.securepw.core;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class CoreTest {
	@Before
	public void before() {
		File rf = new File(resourceFolder);
		if (!rf.exists())
			rf.mkdirs();
	}

	String resourceFolder = "resources/test";

	@Test
	public void test() throws Exception {

	}

}
