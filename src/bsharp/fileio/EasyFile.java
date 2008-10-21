package bsharp.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * A helper class to reading a writing files.
 * 
 * @author brandon@sharpideas.ca
 */
public class EasyFile extends File {

   private BufferedWriter writer;
   private BufferedReader reader;

   /**
    * Create helper class given the filename.
    * @param filename the file that may be written or read.
    */
   public EasyFile( final String _filename) {
      super(_filename);
   }

   /**
    * Create helper class given the filename.
    * @param filename the file that may be written or read.
    */
   public EasyFile( final File _file) {
      super(_file.getAbsolutePath());
   }

   /**
    * Open a file for reading.
    * @param name
    * @return
    */
   public void openForReading() {
      try {
         reader = new BufferedReader(new FileReader(this));
      } catch (final FileNotFoundException e ) {
         e.printStackTrace();
      }
   }

   /**
    * Open a file for writing.
    * @return
    */
   public void openForWriting() {
      try {
         writer = new BufferedWriter(new FileWriter(this));
      } catch (final Exception e ) {
         e.printStackTrace();
      }
   }

   /**
    * Open a file for writing.
    * @param append if true then append to the end of the file.
    * @return
    */
   public void openForWriting(final boolean append) {
      try {
         writer = new BufferedWriter(new FileWriter(this.getAbsolutePath(), append));
      } catch (final Exception e ) {
         e.printStackTrace();
      }
   }

   /**
    * Write to the file.
    * Must call openForWriting() first.
    * @param s
    */
   public void write( final String s) {
      try {
         writer.write(s);
      } catch (final Exception e ) {
         e.printStackTrace();
      }
   }

   /**
    * Write to the file.
    * Must call openForWriting() first.
    * @param s
    */
   public void writeln( final String s) {
      try {
         writer.write(s);
         writer.newLine();
      } catch (final Exception e ) {
         e.printStackTrace();
      }
   }

   /**
    * Read a line from the file.
    * Must call openForReading() first.
    * @return
    */
   public String readLine() {
      try {
         return reader.readLine();
      } catch (final Exception e ) {
         e.printStackTrace();
         return null;
      }
   }

   /**
    * close file after writing.
    * The partner to openForReading().
    */
   public void closeAfterRead() {
      try {
         reader.close();
      } catch (final Exception e ) {
         e.printStackTrace();
      }
   }

   /**
    * close file after writing.
    * The partner to openForWriting().
    */
   public void closeAfterWrite() {
      try {
         writer.close();
      } catch (final Exception e ) {
         e.printStackTrace();
      }
   }

}
