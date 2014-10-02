package headfirst.command.simpleremote;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LightTest {
	private Light light;
	private ByteArrayOutputStream stream;
	private PrintStream print;

	@Before
	public void setUp(){
		light = new Light();
		stream = new ByteArrayOutputStream();
		print = new PrintStream(stream);
	}
	
	@Test
	public void testOn(){
		System.setOut(print);
		light.on();
		print.flush();
		String actual = stream.toString().trim();
		assertEquals("Light is on",actual);
	}
	
	@Test
	public void testOff(){
		System.setOut(print);
		light.off();
		print.flush();
		String actual = stream.toString().trim();
		assertEquals("Light is off",actual);
	}
	
	@After
	public void tearDown(){
		light = null;
		stream = null;
		print = null;
	}

}
