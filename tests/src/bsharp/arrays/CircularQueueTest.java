package bsharp.arrays;

import junit.framework.TestCase;

public class CircularQueueTest extends TestCase {

   protected void setUp() throws Exception {
      super.setUp();
   }

   protected void tearDown() throws Exception {
      super.tearDown();
   }

   private static final String[] OBJS = new String[] {
      "a", "b", "c", "d", "e"
   };
   
   private void testAdd(final CircularQueue q, final Object o, final boolean trace ) {
      if (trace) System.out.println("q.add(" + o + ")");
      q.add(o);
      testCurrent(q, o, false);
   }
   
   private void testBack(final CircularQueue q, final Object o, final boolean trace ) {      
      Object result = q.back();
      if (trace) System.out.println("q.back(): " + result);
      assertEquals(o, result); 
   }
   
   private void testForward(final CircularQueue q, final Object o, final boolean trace ) {      
      Object result = q.forward();
      if (trace) System.out.println("q.forward(): " + result);
      assertEquals(o, result); 
   }
   
   private void testCurrent(final CircularQueue q, final Object o, final boolean trace) {      
      Object result = q.current();
      if (trace) System.out.println("q.current(): " + result);
      assertEquals(o, result); 
   }
   
   private static final int N = 3;
   
   public void test1() {
      CircularQueue q = new CircularQueue(20);
      Object result = null;
      int index = 0;
      
      q = new CircularQueue(20);
      
      testAdd(q, OBJS[index++], true);
      testAdd(q, OBJS[index++], true);
      testAdd(q, OBJS[index++], true);
      testAdd(q, OBJS[index], true);
      
      testCurrent(q, OBJS[index], true);
      
      testBack(q, OBJS[--index], true);
      testBack(q, OBJS[--index], true);
      testBack(q, OBJS[--index], true);
      testBack(q, null, true);
      testBack(q, null, true);
      testBack(q, null, true);
    
      // Test back until there is no more
      q = new CircularQueue(2);
      index = 0;
      
      testAdd(q, OBJS[index++], true);
      testAdd(q, OBJS[index], true);
      
      testCurrent(q, OBJS[index], true);
      
      testBack(q, OBJS[--index], true);
      testBack(q, null, true);

      // Adding over the capacity
      q = new CircularQueue(2);
      index = 0;
      
      testAdd(q, OBJS[index++], true);
      testAdd(q, OBJS[index++], true);
      testAdd(q, OBJS[index++], true);
      testAdd(q, OBJS[index], true);
      
      testCurrent(q, OBJS[index], true);
      
      testBack(q, OBJS[--index], true);
      testBack(q, null, true);
      
      // Test back until there is no more
      // Then forward
      q = new CircularQueue(2);
      index = 0;
      
      testAdd(q, OBJS[index++], true);
      testAdd(q, OBJS[index], true);
      
      testCurrent(q, OBJS[index], true);
      
      testBack(q, OBJS[--index], true);
      for (int i = 0; i < N; i++) {
         testBack(q, null, true); // <-- can do this arbitrary number of times   
      }      
      testForward(q, OBJS[++index], true); // and this should still be correct

      // Test forward until there is no more
      // Then back
      q = new CircularQueue(2);
      index = 0;
      
      testAdd(q, OBJS[index++], true);
      testAdd(q, OBJS[index], true);
      
      testCurrent(q, OBJS[index], true);
      
      testBack(q, OBJS[--index], true);
      for (int i = 0; i < N; i++) {
         testBack(q, null, true); // <-- can do this arbitrary number of times   
      }      
      testForward(q, OBJS[++index], true); // and this should still be correct
      for (int i = 0; i < N; i++) {
         testForward(q, null, true); // <-- can do this arbitrary number of times   
      }
      testBack(q, OBJS[--index], true); // and this should still be correct
   }
   
   public void testCircularQueue() {
      CircularQueue q = new CircularQueue(20);
      Object result = null;
      
      
      result = q.back();
      assertEquals(null, result);
      System.out.println("q.back(): " + result);
      
      q = new CircularQueue(20);
      
      result = q.forward();
      assertEquals(null, result);      
      System.out.println("q.forward(): " + result);
      
      q = new CircularQueue(20);
      
      result = q.current();
      assertEquals(null, result);  
      System.out.println("q.current(): " + result);
      
   }

   public void testClear() {
      fail("Not yet implemented");
   }

   public void testAdd() {
      fail("Not yet implemented");
   }

   public void testCurrent() {
      fail("Not yet implemented");
   }

   public void testPeekForward() {
      fail("Not yet implemented");
   }

   public void testForward() {
      fail("Not yet implemented");
   }

   public void testForwardBoolean() {
      fail("Not yet implemented");
   }

   public void testBack() {
      fail("Not yet implemented");
   }

}
