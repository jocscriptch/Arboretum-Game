package util;


import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import com.sun.security.ntlm.Client;

import java.io.*;
import java.net.*;

public class ClientConnection {
    private Socket conecction;
    private DataInputStream readInformation;
    private DataOutputStream writeInformation;
    private static ClientConnection instance;

    public static ClientConnection getInstance(String ipAddress){
        if(instance == null){
            instance = new ClientConnection(ipAddress);
        }
        return instance;
    }
    private ClientConnection(String ipAddress){
        try{
            conecction = new Socket(ipAddress, 8000);
            readInformation = new DataInputStream(new BufferedInputStream(conecction.getInputStream()));
            writeInformation = new DataOutputStream(new BufferedOutputStream(conecction.getOutputStream()));
        }catch (UnknownHostException uhe){
            System.out.println("Access denied");
        }catch (IOException ioe){
            System.out.println("Error in the connection: "+ ioe.getMessage());
        }
    }
    public DataInputStream getReadInformation(){
        return readInformation;
    }
    public DataOutputStream getWriteInformation(){
        return writeInformation;
    }
}
