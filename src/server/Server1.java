package server;

import bean.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Server1 {
    public static void main(String[] args) throws Exception {
        List<User> usrList = new ArrayList<User>();
        ServerSocket serverSocket = new ServerSocket(20001);
        Socket client = null;
        Executor service = Executors.newCachedThreadPool();
        //Executor service = new ThreadPoolExecutor(0, 2, 60L, TimeUnit.SECONDS, new SynchronousQueue(),new ThreadPoolExecutor.DiscardPolicy());
        try {
            boolean flag= true;
            while (flag) {
                System.out.println("正在监听客户端连接....");
                client = serverSocket.accept();
                System.out.println("与客户端连接成功！");
                PrintStream sendOut = new PrintStream(client.getOutputStream());
                BufferedReader rcvIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
                User usr = new User("usr"+Math.round(Math.random() * 100),client);
                usr.setSendOut(sendOut);
                usr.setRcvIn(rcvIn);
                usrList.add(usr);
                service.execute(new ConnectionThread(usr,usrList));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverSocket.close();
    }
}
