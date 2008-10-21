package bsharp.arrays;

/**
 * A static class with helpful methods to manipulating arrays.
 *
 * @author brandon@sharpideas.ca
 */
public class Arrays {

   private Arrays() {
      // hide constructor
   }

   /**
    * Return true if the array contains the given object.
    * Useful pre-Java 1.2
    *
    * @param array the array to search
    * @param element the element to search for.
    *
    * @return true if the array contains the given object.
    */
   public static boolean contains( final Object[] array, final Object element) {
      for ( int i = 0 ; i < array.length; i++ ) {
         if ( array[i].equals( element )) {
            return true;
         }
      }
      return false;
   }
}
