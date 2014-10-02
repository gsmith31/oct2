package headfirst.command.simpleremote;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LightOnCommandTest {
	private Light light;
	private LightOnCommand lightOn;
	private ByteArrayOutputStream stream;
	private PrintStream print;

	@Before
	public void setUp(){
		light = new Light();
		lightOn = new LightOnCommand(light);
		stream = new ByteArrayOutputStream();
		print = new PrintStream(stream);
	}
	
	@Test
	public void testOn(){
		System.setOut(print);
		lightOn.execute();
		print.flush();
		String actual = stream.toString().trim();
		assertEquals("Light is on",actual);
	}
	

	
	@After
	public void tearDown(){
		light = null;
		stream = null;
		print = null;
	}

}
