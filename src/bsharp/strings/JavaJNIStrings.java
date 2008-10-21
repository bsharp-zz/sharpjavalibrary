package bsharp.strings;

/**
 * Helper methods for parsing java jni strings.
 *
 * @author brandon@sharpideas.ca
 *
 */
public class JavaJNIStrings {

   /**
    * Pacakage, Class, and method name separator.
    */
   protected static final String DOT = ".";

   /**
    * Converts an absolute file location and converts it to a java classname.
    * @param fullpath full file system path.
    * @param srcDirPrefix the part of the path up until the java source directories.
    * @return
    */
   public static String fullPathToClassname( final String fullpath, final String srcDirPrefix ) {

      // Remove preceding path
      String classname = fullpath.substring( srcDirPrefix.length(), fullpath.length());

      // Remove .java
      classname = Strings.subtractRight(classname, ".java" );

      // Replace file delimiter with package delimiters
      classname = Strings.replaceAll(classname, "/", DOT);
      classname = Strings.replaceAll(classname, "\\", DOT);

      return classname;
   }
}
