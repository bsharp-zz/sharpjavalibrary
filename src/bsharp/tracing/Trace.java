package bsharp.tracing;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import bsharp.strings.Strings;


/**
 * A useful collection of commonly used tracing functionality including:
 *    All trace messages are sychronized so messages from different threads don't interleave
 *    All trace messages are flush()-ed for convenience
 *    All Trace messages are prepened with the current classname and method.
 *    A method to get the current method name
 *    A method to get a classes 'simpleName' not availible until Java 1.5
 *    A method to print out all the current running threads.
 *
 * @author brandon@sharpideas.ca
 *
 */
public class Trace {

   /**
    * Static methods only.
    */
   private Trace() {
      // hide constructor
   }

   /**
    * Get the string representation of a Classes 'simple name' with
    * Java 1.1 compatibility. Like Class.getSimpleName() in Java 1.5.
    */
   public static String getClassSimpleName( final Class aClass ) {
      return Strings.subtractLeft(aClass.getName() ,
            aClass.getPackage().getName() + ".");
   }

   /**
    * Prints a message, synchronized.
    * <br/>- Also flushes System.out.
    * <br/>- And synchronizes messages on the Trace class.
    */
   public static void println(final String message) {
      synchronized (Trace.class) {
         System.out.println(message);
         System.out.flush();
      }
   }

   /**
    * Prints a message, synchronized, and Prefixed with Class and method name.
    * <br/>- Also flushes System.out.
    * <br/>- And synchronizes messages on the Trace class.
    */
   public static void printlnP(final String message) {
      println(getCurrentClassAndMethodName(1) + "(): " + message);
   }

   /**
    * Prints message to System.err.
    * <br/>- Also flushes System.err.
    * <br/>- And synchronizes messages on the Trace class.
    */
   public static void errPrintln(final String message) {
      synchronized (Trace.class) {
         System.err.println(message);
         System.err.flush();
      }
   }
   
   /**
    * Prints message to System.err. Prefixed with Class and method name.
    * <br/>- Also flushes System.err.
    * <br/>- And synchronizes messages on the Trace class.
    */
   public static void errPrintlnP(final String message) {
      errPrintln(getCurrentClassAndMethodName(1) + "(): " + message);
   }
   
   /**
    * Prints out the currently running threads.
    * <br/>- Also flushes System.out.
    * <br/>- And synchronizes messages on the Trace class.
    */
   public static void printThreads() {
      checkThreads("");
   }

   /**
    * Prints out the currently running threads, Prefixed with current class and method name.
    * <br/>- Also flushes System.out.
    * <br/>- And synchronizes messages on the Trace class.
    */
   public static void printThreadsP() {
      checkThreads(getCurrentClassAndMethodName(1) + "()");
   }

   /*
    * Prints out the currently running threads
    * @param prefix who called this method
    */
   private static void checkThreads(String prefix) {

      final ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
      final int activeCount = threadGroup.activeCount();
      final Thread[] activeThreads = new Thread[activeCount + 10];
      final int numActiveThreads = threadGroup.enumerate(activeThreads);
      prefix = "[" + prefix + "][CheckThreads] ";

      println(prefix + "activeCount = " + activeCount);
      println(prefix + "numActiveThreads = " + numActiveThreads);

      for ( int i = 0; i < numActiveThreads; ++i ) {
         println(prefix +
               (activeThreads[i].isAlive() ? "Alive" : "Dead") +
               ((activeThreads[i].isDaemon()) ? " Daemon Thread[" : " User Thread[")
               + i + "] = " + activeThreads[i].getName());
      }
   }

   /**
    * Return the name of the routine that called getCurrentMethodName.
    */
   public static String getCurrentMethodName() {
      return getCurrentMethodName( 1 );
   }

   /**
    * Return the name of the routine that called getCurrentMethodName.
    *
    * @param depth where 0 is the method called getCurrentMethodName(),
    *              1 is the method that called that etc.
    */
   public static String getCurrentMethodName( final int depth ) {
      final String fullname = getCurrentCallerFullName( depth + 1 );

      return getMethodName( fullname ) ;
   }

   /**
    * Return the name of class and method that called getCurrentMethodName.
    */
   public static String getCurrentClassAndMethodName() {
      return getCurrentClassAndMethodName(1);
   }

   /**
    * Return the name of class and method that called getCurrentMethodName.
    *
    * @param depth where 0 is the method called getCurrentMethodName(),
    *              1 is the method that called that etc.
    */
   public static String getCurrentClassAndMethodName( final int depth ) {
      final String fullname = getCurrentCallerFullName( depth + 1 );

      return getClassAndMethodName(fullname);
   }

   private static String getCurrentCallerFullName( final int depth ) {
      final ByteArrayOutputStream baos = new ByteArrayOutputStream();
      final PrintWriter pw = new PrintWriter(baos);

      (new Throwable()).printStackTrace(pw);
      pw.flush();
      pw.close();

      final String stackTrace = baos.toString();

      StringTokenizer tok = new StringTokenizer(stackTrace, "\n");
      String l = tok.nextToken(); // Skip line: 'java.lang.Throwable'
      l = tok.nextToken(); // Skip line: 'at ...getCurrentMethodName'
      for ( int i = 0; i < depth + 1; i++ ) {
         l = tok.nextToken();
      }

      // Remove "(<JavaFile.java>:<linenumber>)"
      tok = new StringTokenizer(l.trim(), " (");
      String t = tok.nextToken(); // Skip 'at'

      return tok.nextToken();
   }

   private static String getMethodName( final String fullName ) {
      return fullName.substring( fullName.lastIndexOf(".") + 1, fullName.length() );
   }
   
   private static String getClassAndMethodName( final String fullName ) {
      // Last '.'
      final String temp = fullName.substring( 0, fullName.lastIndexOf(".") );
      // Second last '.'
      return fullName.substring( temp.lastIndexOf(".") + 1, fullName.length() );
   }
   
   /**
    * Trace a byte array in hex, synchronized.
    * @param data
    */
   public static void traceHex(byte[] data ) {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < data.length; i++) {
         sb.append( Integer.toHexString(data[i] & 0xFF) );
         sb.append("(");
         sb.append( data[i] );
         if ( data[i] >= 33 && data[i] <= 126 ) // is printable
            sb.append(","+(char)data[i]);
         sb.append(") ");
      }
      println( sb.toString() );
   }

   public static String arrayToString( final Object[] array ) {
      StringBuffer sb = new StringBuffer("Array: [ ");
      for( int i = 0; i < array.length; i++ ){
         sb.append( array[i]);
         sb.append(", ");
      }
      sb.append("]");
      return sb.toString();
   }
}
