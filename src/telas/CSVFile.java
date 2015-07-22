/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import java.io.*;
import java.util.*;

/** CSV in action: lines from a file and print. */
public class CSVFile {

  public static void main(String[] args) throws IOException {
  
    // Construct a new CSV parser.
    CSV csv = new CSV();

    if (args.length == 0) {  // read standard input
      BufferedReader is = new BufferedReader(
        new InputStreamReader(System.in));
      process(csv, is);
    } else {
      for (int i=0; i<args.length; i++) {
//        process(csv, new BufferedReader(new FileReader(args[i])));
//        process(csv, new BufferedReader(new FileReader("C:\\Users\\leleco\\Documents\\NetBeansProjects\\Monitor\\src\\telas\\node-a.log")));
        process(csv, new BufferedReader(new FileReader("C:\\Users\\leleco\\Documents\\NetBeansProjects\\Monitor\\src\\telas\\node-a.log")));
      }
    }
  }

  protected static void process(CSV csv, BufferedReader is) throws IOException {
    String line;
    while ((line = is.readLine()) != null) {
      System.out.println("line = `" + line + "'");
      Iterator e = csv.parse(line).iterator();
      int i = 0;
      while (e.hasNext()) 
        System.out.println("field[" + i++ + "] = `" +
          e.next() + "'");
    }
  }
}