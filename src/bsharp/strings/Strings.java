package bsharp.strings;

/**
 * Helper methods for string modifcations.
 * @author brandon@sharpideas.ca
 */
public class Strings {

   /** NEW LINE. */
   public static final String NL = "\n";
   /** A SPACE. */
   public static final String SPACE = " ";
   /** A QUOTE. */
   public static final String QUOTE = "\"";

   /**
    * Only static methods.
    */
   private Strings() {

   }

   /**
    * Subtract the second string from the right side of the first.
    * @param source the first string.
    * @param subtract subtract this from the first string.
    * @return the remainder of the first string.
    */
   public static String subtractRight( final String source, final String subtract) {
      return source.substring(0, source.length() - subtract.length());
   }

   /**
    * Subtract the second string from the left side of the first.
    * @param source the first string.
    * @param subtract subtract this from the first string.
    * @return the remainder of the first string.
    */
   public static String subtractLeft( final String source, final String subtract) {
      return source.substring(subtract.length(), source.length());
   }

   /**
    * Prepend the given character until the string is the width of targetSize.
    * @param s the string to indent.
    * @param fill the character to prepend.
    * @param targetSize the size to make the final string.
    * @return
    */
   public static String indent( final String s, final char fill, final int targetSize ) {
      final StringBuffer sb = new StringBuffer();

      while ( sb.length() + s.length() < targetSize) {
         sb.append( fill );
      }
      sb.append( s );
      return sb.toString();
   }

   /**
    * Enclose the given string in quotes.
    * @param s the input string.
    * @return the string enclosed in quotes.
    */
   public static String quote( final String s ) {
      return new StringBuffer(QUOTE).append(s).append(QUOTE).toString();
   }

   /**
    * Replace all occurrences of the character with the replacement string.
    * @param input the input string.
    * @param match
    * @param replaceWith
    * @return the input string with all occurrences of the character with the replacement string.
    */
   public static String replaceAll(
         final String input, final String match, final String replaceWith ) {

      final int oldLen = match.length();
      final StringBuffer result = new StringBuffer();

      int index;
      String remainder = input;
      while ( (index = remainder.indexOf(match)) != -1 ) {

         // Append Characters before match / after last match
         result.append(remainder.substring(0, index));

         // Append new pattern
         result.append(replaceWith);

         // Remove the what has been processed so far.
         remainder = remainder.substring( index + oldLen );
      }

      // Append Characters before match / after last match
      result.append(remainder);

      return result.toString();
   }
}
