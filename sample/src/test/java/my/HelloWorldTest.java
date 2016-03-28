package my;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HelloWorldTest {

	@Test
	public void test() {
		HelloWorld hello = new HelloWorld();
		assertEquals("Hello World!", hello.getGreeting());
	}

	@Test
	public void assertion() {
		String actual = "Hello" + " " + "World";
		assertThat(actual, is("Hello World"));
	}
}
