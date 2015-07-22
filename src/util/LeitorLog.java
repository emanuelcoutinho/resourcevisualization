/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import telas.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import monitor.Principal;

public class LeitorLog {

//    public static final String nodea = "/home/emanuel/NetBeansProjects/Monitor/src/arquivosTexto/node-a.log";
//    public static final String nodeb = "/home/emanuel/NetBeansProjects/Monitor/src/arquivosTexto/node-b.log";
//    public static final String nodec = "/home/emanuel/NetBeansProjects/Monitor/src/arquivosTexto/node-c.log";
//    public static final String media = "/home/emanuel/NetBeansProjects/Monitor/src/arquivosTexto/media.log";
//    public static final String alocacao = "/home/emanuel/NetBeansProjects/Monitor/src/arquivosTexto/alocacao.log";
//    public static final String diretorioBase = "C:\\Users\\leleco\\Documents\\NetBeansProjects\\Monitor\\src\\arquivosTexto\\";
    public static String diretorioBase = "C:\\Users\\leleco\\Documents\\NetBeansProjects\\Monitor\\src\\arquivosTexto\\";
    public static String nodea = diretorioBase + "node-a.log";
    public static String nodeb = diretorioBase + "node-b.log";
    public static String nodec = diretorioBase + "node-c.log";
    public static String noded = diretorioBase + "node-d.log";
    public static String media = diretorioBase + "media.log";
    public static String alocacao = diretorioBase + "alocacao.log";
    public static String tomcat1 = diretorioBase + "tom1.log";
    public static String tomcat2 = diretorioBase + "tom2.log";
    public static String tomcat3 = diretorioBase + "tom3.log";
    public static String tomcat4 = diretorioBase + "tom4.log";
    public static String elasticidadefisica = diretorioBase + "elasticidadefisica.log";
    
    public static void atualizaDiretorios(String d) {
        diretorioBase = d;
        nodea = diretorioBase + "node-a.log";
        nodeb = diretorioBase + "node-b.log";
        nodec = diretorioBase + "node-c.log";
        noded = diretorioBase + "node-d.log";
        media = diretorioBase + "media.log";
        alocacao = diretorioBase + "alocacao.log";
        tomcat1 = diretorioBase + "tom1.log";
        tomcat2 = diretorioBase + "tom2.log";
        tomcat3 = diretorioBase + "tom3.log";
        tomcat4 = diretorioBase + "tom4.log";
        elasticidadefisica = diretorioBase + "elasticidadefisica.log";
        }

    public static ArrayList arquivo(String arquivo) throws IOException {
        String linha = null;
        ArrayList listaLog = new ArrayList();
        try {

            //FileReader reader = new FileReader("/home/ubuntu/monitor/node-a.log");
            FileReader reader = new FileReader(arquivo);
            BufferedReader leitor = new BufferedReader(reader);
            StringTokenizer st = null;

            while ((linha = leitor.readLine()) != null) {

                st = new StringTokenizer(linha, "|");
                String dados = null;
                entidade.Log l = new entidade.Log();

                l.setData(st.nextToken());
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
            throw new IOException();
          //  e.printStackTrace();
           // return null;
        }
    }

    public static ArrayList arquivoUmaColuna(String arquivo) {
        String linha = null;
        ArrayList listaLog = new ArrayList();
        try {

            FileReader reader = new FileReader(arquivo);
            BufferedReader leitor = new BufferedReader(reader);
            StringTokenizer st = null;

            while ((linha = leitor.readLine()) != null) {

//                st = new StringTokenizer(linha, "|");
                //              String dados = null;
                entidade.Log l = new entidade.Log();

                l.setCpuUser(converteStringDouble(linha));
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

    // utiliza o cpuuser para tudo
    public static ArrayList arquivoColuna(String arquivo, int coluna) throws IOException{
        String linha = null;
        ArrayList listaLog = new ArrayList();
        try {
            FileReader reader = new FileReader(arquivo);
            BufferedReader leitor = new BufferedReader(reader);
            StringTokenizer st = null;

            while ((linha = leitor.readLine()) != null) {

                st = new StringTokenizer(linha, "|");
                String dados = null;
                entidade.Log l = new entidade.Log();
                double d = 0;
                String s = null;
                for (int i = 1; i <= coluna; i++) {
                    s = st.nextToken();
                }
                d = converteStringDouble(s);
                l.setCpuUser(d);
                System.out.println(l.toString());
                listaLog.add(l);
            }
            leitor.close();
            reader.close();
            return listaLog;

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException();
            //return null;
        }
    }

    public static double converteStringDouble(String s) {
        s = s.replaceAll(",", ".");
        return Double.parseDouble(s);
    }

      // utiliza o texto para tudo
    public static ArrayList arquivoColunaTexto(String arquivo, int coluna) throws IOException{
        String linha = null;
        ArrayList listaLog = new ArrayList();
        try {
            FileReader reader = new FileReader(arquivo);
            BufferedReader leitor = new BufferedReader(reader);
            StringTokenizer st = null;

            while ((linha = leitor.readLine()) != null) {

                st = new StringTokenizer(linha, "|");
                String dados = null;
                entidade.Log l = new entidade.Log();
                double d = 0;
                String s = null;
                for (int i = 1; i <= coluna; i++) {
                    s = st.nextToken();
                }
                l.setTexto(s);
                System.out.println(l.toString());
                listaLog.add(l);
            }
            leitor.close();
            reader.close();
            return listaLog;

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException();
            //return null;
        }
    }

        public static ArrayList arquivoElasticidade(String arquivo) throws IOException {
        String linha = null;
        ArrayList listaLog = new ArrayList();
        try {

            //FileReader reader = new FileReader("/home/ubuntu/monitor/node-a.log");
            FileReader reader = new FileReader(arquivo);
            BufferedReader leitor = new BufferedReader(reader);
            StringTokenizer st = null;

            while ((linha = leitor.readLine()) != null) {

                st = new StringTokenizer(linha, "|");
                String dados = null;
                entidade.Log l = new entidade.Log();

                l.setData(st.nextToken());
                l.setCpuUser(converteStringDouble(st.nextToken()));
                l.setCpuSys(converteStringDouble(st.nextToken()));
                l.setCpuIdle(converteStringDouble(st.nextToken()));
                l.setMemoriaTotal(converteStringDouble(st.nextToken()));
                l.setMemoriaUsed(converteStringDouble(st.nextToken()));
                l.setMemoriaFree(converteStringDouble(st.nextToken()));
                System.out.println(l.toString());
                listaLog.add(l);
            }
            leitor.close();
            reader.close();
            return listaLog;

        } catch (Exception e) {
            throw new IOException();
        }
    }

    
    
    
    public static void main(String[] args) {
        Teste1 t = new Teste1();
        t.arquivo();
    }
}
