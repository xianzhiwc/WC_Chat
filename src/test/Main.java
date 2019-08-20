package test;

import bean.User;
import client.Client1;
import client.ClientThread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",20001);
        //socket.setSoTimeout(10000);
        ClientThread ct = new ClientThread(socket);
        ct.start();
        User usr = new User();
        usr.setIp(InetAddress.getLocalHost().getHostAddress());
        usr.setPort(5030);
        usr.setName("xianzhiwc");
        usr.setAccount("1425420712");
        Client1.sendMessage(socket,usr);
    }
}
