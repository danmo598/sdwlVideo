package com.sdwl.video.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class CmdExecuter {


	public static void main(String[] args) {

		// TODO Auto-generated method stub 

	}
	
	
    /** 
     * ִ��ָ�� 
     * @param cmd ִ��ָ�� 
     * @param getter ָ��ش���ӿڣ���Ϊnull�򲻴������ 
     */  
    static public boolean exec( List<String> cmd, IStringGetter getter ){
        boolean flag = true;
        try {  
            ProcessBuilder builder = new ProcessBuilder();    
            builder.command(cmd);  
            builder.redirectErrorStream(true);  
            Process proc = builder.start();  
            BufferedReader stdout = new BufferedReader(  
                    new InputStreamReader(proc.getInputStream()));  
            String line;  
            while ((line = stdout.readLine()) != null) {  
                if( getter != null )  
                    getter.dealString(line);  
            }  
            proc.waitFor();     
            stdout.close();  
        } catch (Exception e) {  
            e.printStackTrace();
            flag = false ;

        }
        return flag;
    }

}

