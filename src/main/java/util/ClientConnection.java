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

    public static final String IP_SERVER = "192.168.100.14";

    public static ClientConnection getInstance(){
        if(instance == null){
            instance = new ClientConnection();
        }
        return instance;
    }
    private ClientConnection(){
        try{
            conecction = new Socket(IP_SERVER, 8000);
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
    public String responseMessageToServer() {
        byte[] buffer = new byte[1024];
        try {
            int bufferData = readInformation.read(buffer);
            String response = new String(buffer, 0, bufferData, "UTF-8");
            return response;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return null;
    }
    public void sendMessageToServer(String message){
        try{
            writeInformation.write(message.getBytes());
            writeInformation.flush();
        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
}

