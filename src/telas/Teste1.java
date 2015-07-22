/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Teste1 {

    public static ArrayList arquivo() {
        String linha = null;
        ArrayList listaLog = new ArrayList();
        try {

            //FileReader reader = new FileReader("/home/ubuntu/monitor/node-a.log");
            FileReader reader = new FileReader("C:\\Users\\leleco\\Documents\\NetBeansProjects\\Monitor\\src\\telas\\node-a.log");
            BufferedReader leitor = new BufferedReader(reader);
            StringTokenizer st = null;

            while ((linha = leitor.readLine()) != null) {

                st = new StringTokenizer(linha, "|");
                String dados = null;
                Log l = new Log();

                l.setCpuUser(converteStringDouble(st.nextToken()));
                l.setCpuSys(converteStringDouble(st.nextToken()));
                l.setCpuIdle(converteStringDouble(st.nextToken()));
                l.setMemoriaTotal(converteStringDouble(st.nextToken()));
                l.setMemoriaUsed(converteStringDouble(st.nextToken()));
                l.setMemoriaFree(converteStringDouble(st.nextToken()));
                l.setDiscoRead(converteStringDouble(st.nextToken()));
                l.setDiscoWrite(converteStringDouble(st.nextToken()));
                l.setRedeIn(converteStringDouble(st.nextToken()));
                l.setRedeOut(converteStringDouble(st.nextToken()));

//                while (st.hasMoreTokens()) {
//                    dados = st.nextToken();
//                    System.out.println(dados);
//
//                }
//                System.out.println("-------");  
                System.out.println(l.toString());
                listaLog.add(l);
            }
            leitor.close();
            reader.close();
            return listaLog;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static double converteStringDouble(String s) {
        s = s.replaceAll(",", ".");
        return Double.parseDouble(s);
    }

    public static void main(String[] args) {
        Teste1 t = new Teste1();
        t.arquivo();
    }
}
