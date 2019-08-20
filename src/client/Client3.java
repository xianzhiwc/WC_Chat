package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Client3 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",20001);
        socket.setSoTimeout(10000);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintStream sendOut = new PrintStream(socket.getOutputStream());
        BufferedReader rcvIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        boolean flag= true;
        try {
            while(flag){
                System.out.println("输入信息：");
                String info = input.readLine();
                sendOut.println(info);
                if("bye".equals(info) || "BYE".equals(info)){
                    flag= false;
                }else {
                    try {
                        String echo = rcvIn.readLine();
                        System.out.println(echo);
                    } catch (SocketTimeoutException e) {
                        System.out.println("Time out,No response");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            input.close();
            sendOut.close();
            rcvIn.close();
        }
        if(socket !=null){
            socket.close();
        }
    }
}
