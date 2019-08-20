package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerPool {
    public static final int THREAD_POOL_SiZE = 2;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(20001);
        for (int i = 0;i<THREAD_POOL_SiZE;i++){
            new Thread(){
                public void run(){
                    while (true){
                        try {
                            System.out.println("监听客户端....");
                            Socket client = serverSocket.accept();
                            System.out.println("连接成功！");
                            //ConnectionThread.execute(client);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
            }.start();
        }
    }
}
