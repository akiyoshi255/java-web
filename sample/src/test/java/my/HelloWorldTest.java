package my;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelloWorldTest {

	@Test
	public void test() {
		HelloWorld hello = new HelloWorld();
		assertEquals("Hello World!", hello.getGreeting());
	}
}
