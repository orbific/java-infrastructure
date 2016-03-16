package com.riddlefox.greeting;

import com.riddlefox.greeting.Greeter;
import static org.junit.Assert.*;
import org.junit.Test;

// This is named to make it easier to see in lists
public class GreeterTest { 
	
   @Test
   public void testHelloWorld() 
   {
      Greeter greeter = new Greeter();
      String response = greeter.greet("World");
      assertEquals("String should be as expected", "Hello World", response);
   }
}
