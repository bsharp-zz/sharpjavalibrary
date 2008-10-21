package bsharp.strings;

import junit.framework.TestCase;

public class StringsTest extends TestCase {

   protected void setUp() throws Exception {
      super.setUp();
   }

   protected void tearDown() throws Exception {
      super.tearDown();
   }

   public void testSubtractRight() {
      fail("Not yet implemented");
   }

   public void testSubtractLeft() {
      fail("Not yet implemented");
   }

   public void testIndent() {
      fail("Not yet implemented");
   }

   public void testQuote() {
      fail("Not yet implemented");
   }

   public void testReplaceAll() {
      try {
         System.out.println(Strings.replaceAll("in#####", "in", "out"));
         System.out.println(Strings.replaceAll("#####in#####", "in", "out"));
         System.out.println(Strings.replaceAll("#####in", "in", "out"));
         System.out.println(Strings.replaceAll("#####in##in##in###", "in", "out"));
         System.out.println(Strings.replaceAll("in#####in##in##in###in", "in", "out"));
         System.out.println(Strings.replaceAll("##inin##", "in", "out"));
      } catch (Throwable e) {
         e.printStackTrace();
      }
   }

}
