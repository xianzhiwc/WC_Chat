package bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class User {
    private String name;
    private String account;
    private String ip;
    private int port;
    private String mac;
    private Socket socket;
    PrintStream sendOut = null;
    BufferedReader rcvIn = null;

    public User(String name,Socket s) throws IOException {
        this.name = name;
        this.socket = s;
        this.sendOut = new PrintStream(s.getOutputStream());
        this.rcvIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }
    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public PrintStream getSendOut() {
        return sendOut;
    }

    public void setSendOut(PrintStream sendOut) {
        this.sendOut = sendOut;
    }

    public BufferedReader getRcvIn() {
        return rcvIn;
    }

    public void setRcvIn(BufferedReader rcvIn) {
        this.rcvIn = rcvIn;
    }



}
