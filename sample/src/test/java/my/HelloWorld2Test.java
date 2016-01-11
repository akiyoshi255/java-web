package my;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelloWorld2Test {

	@Test
	public void test() {
		HelloWorld hello = new HelloWorld();
		assertEquals("Hello World!", hello.getGreeting());
	}
}
