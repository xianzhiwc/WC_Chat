package test;

import bean.User;
import client.Client2;
import client.ClientThread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Main2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",20001);
        //socket.setSoTimeout(10000);
        ClientThread ct = new ClientThread(socket);
        ct.start();
        User usr = new User();
        usr.setIp(InetAddress.getLocalHost().getHostAddress());
        usr.setPort(5031);
        usr.setName("Prophet");
        usr.setAccount("2670700037");
        Client2.sendMessage(socket,usr);
    }
}
