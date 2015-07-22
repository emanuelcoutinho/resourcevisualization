/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;

/**
 *
 * @author leleco
 */
public class Datas {

    public static Date stringToDataTomcat(String s) throws ParseException {
        //"[12/Sep/2013:21:52:30 +0000]"
        String replace = s.replace("+00", "-03"); // tive que trocar pq o tomcat só retornava +0000
//        SimpleDateFormat sdf = new SimpleDateFormat("[dd/MMM/yyyy:HH:mm:ss Z]", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("[dd/MMM/yyyy:HH:mm:ss:SSS Z]", Locale.ENGLISH);
        Date data = sdf.parse(replace);
        return data;
    }

    public static Date stringToDataShellScript(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        Date data = sdf.parse(s);
        return data;
    }

        public static Date stringToDataElasticidade(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date data = sdf.parse(s);
        return data;
    }

    public static void main(String[] args) throws ParseException {

//        String str = "2010-03-13 01:01:22";
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = sf.parse(str);
//        SimpleDateFormat f = new SimpleDateFormat("d MMM yyyy hh:mm aaa");
//        System.out.println(" Date " + f.format(date)); 
//        

        String sss = "13 Mar 2010 01:01";
        SimpleDateFormat ff = new SimpleDateFormat("dd MMM yyyy hh:mm");
        Date d = ff.parse(sss);
        System.out.println(d.toString());

//        String ss = "15/11/2013";
//        SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy"); 
//        Date dataUsuario=sdf1.parse(ss);
//        System.out.println(dataUsuario.toString());
//        
        String s = "[15/Aug/2013:00:03:02 +0000]";
        SimpleDateFormat s1 = new SimpleDateFormat("[dd/MMM/yyyy:HH:mm:ss Z]", Locale.ENGLISH);
        Date d1 = s1.parse(s);
        System.out.println(d1.toString());
//        

//    
//        Date d = new Date(s);
//        RegularTimePeriod p = new Millisecond(100, 1, 1, 1, 1, 1, 2000);
//        
        //    RegularTimePeriod p1 = new Millisecond(dataUsuario);
//        System.out.println(p.toString());
        // System.out.println(p1.toString());


        String a = "2013-08-16 23:09:00.245";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date data = sdf.parse(a);
        System.out.println(data.toString());
        Calendar cal = new GregorianCalendar();
        cal.setTime(data);
        System.out.println("Calendar = " + cal.toString());

    }
}
