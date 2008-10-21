package bsharp.enumerations;

import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.Vector;

/**
 * A static class with helpful methods to manipulating Enumerations;
 *
 * @author brandon@sharpideas.ca
 */
public class Enumerations {

   /**
    * Creates an enumeration out of an array.
    *
    * @throws IllegalArgumentException if the Object is not an array.
    * @return an Enumeration create from the given array.
    */
   public static Enumeration makeEnumeration(final Object obj) {
      final Class type = obj.getClass();
      if (!type.isArray()) {
         throw new IllegalArgumentException(obj.getClass().toString());
      } else {
         return (new Enumeration() {
            final int length = Array.getLength(obj);
            int index;

            public boolean hasMoreElements() {
               return index < length;
            }

            public Object nextElement() {
               return Array.get(obj, index++);
            }
         });
      }
   }

   /**
    * Creates an enumeration out of Vector.
    * (copies vector to array first.)
    *
    * @return an Enumeration create from the given array.
    */
   public static Enumeration makeEnumeration(final Vector v) {

      final Object[] array = v.toArray(new Object[v.size()]);
      return makeEnumeration(array);
   }
}
