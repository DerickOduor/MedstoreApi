package com.derick.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LogFile {

    public void events(String event)
             {
        try{
            String fileName="C:\\Blocker\\EVENT_"+new SimpleDateFormat("yyyyMMdd").format(new Date())+".log";
            String str = "----------"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"----------"+"\n"+event+"\n";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append(' ');
            writer.append(str);

            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void error(Exception exception)
             {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            String sStackTrace = sw.toString();
            String stacktrace = exception.getStackTrace().toString();//ExceptionUtils.getStackTrace(exception);

            String fileName="C:\\Blocker\\ERROR_"+new SimpleDateFormat("yyyyMMdd").format(new Date())+".log";
            String str = "----------"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"----------"+
                    "\n"+exception.getMessage()+"\n"+
                    sStackTrace+
                    "\n";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append(' ');
            writer.append(str);

            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
