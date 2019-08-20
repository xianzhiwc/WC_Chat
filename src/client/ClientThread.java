package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

public class ClientThread extends Thread {
    private Socket socket;

    public ClientThread(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String rcv = br.readLine();
                if (rcv == null){
                    break;
                }else if (!rcv.contains("@")) {
                    System.out.println(rcv);
                } else {
                    String name = rcv.substring(0, rcv.indexOf("|"));
                    String mainInfo = rcv.substring(rcv.indexOf(":") + 1, rcv.lastIndexOf(":"));
                    System.out.println(name + "(" + new Date().toString() + "):\n" + mainInfo);
                    }
                }
            br.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
