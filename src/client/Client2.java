package client;

import bean.Message;
import bean.User;

import java.io.*;
import java.net.Socket;

public class Client2 {
    public static void sendMessage(Socket socket, User usr) throws IOException {
        PrintStream sendOut = new PrintStream(socket.getOutputStream());
        /*服务器转发消息给其他客户端，故注释该行*/
        //BufferedReader rcvIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        boolean flag= true;
        boolean initFlag = true;
        String sendMes = null;
        while (flag) {
            if(initFlag){
                sendMes = "#"+usr.getAccount();
                initFlag = false;
            }else {
                //System.out.println("输入信息：");
                String sendInfo = input.readLine();
                String sign = "EISOFNEXU45648";
                String targetAcount = "1425420712";
                Message ms = new Message().mixMs(sendInfo, sign, targetAcount, usr);
                sendMes = ms.head + ":" + ms.body + ":" + ms.sign + "@" + ms.target;
                }
            BufferedReader sendinfo = new BufferedReader(
                        new InputStreamReader(new ByteArrayInputStream(sendMes.getBytes())));
                try {
                    String info = sendinfo.readLine();
                    sendOut.println(info);
                   //String echo = rcvIn.readLine();
                   //System.out.println(echo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        if(socket !=null){
            socket.close();
        }
    }
}
