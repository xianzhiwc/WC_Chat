package server;

import bean.User;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class ConnectionThread implements Runnable{
    private User usr = null;
    private List<User> usrList;

    public  ConnectionThread(User s,List<User> u) {
        this.usr = s;
        this.usrList =u;
    }
    @Override
    public void run(){
        execute(usr,usrList);
    }

    public static void execute(User s,List<User> list) {
        try {
            boolean f  = true;
            while (f) {
                String info = s.getRcvIn().readLine();
                if(info ==null || "null".equals(info) || "".equals(info)){
                    break;
                }else if ("#".equals(info.substring(0,1))) {
                    String account = info.substring(info.indexOf("#")+1);
                    s.setAccount(account);
                    s.getSendOut().println("初始化连接完成!");
                    System.out.println("初始化连接完成！");
                } else {
                    String mainInfo = info.substring(info.indexOf(":") + 1, info.lastIndexOf(":"));
                    if (mainInfo == null || "null".equals(mainInfo) || "".equals(mainInfo) || "bye".equals(mainInfo) || "BYE".equals(mainInfo)) {
                        break;
                    } else {
                        String target = info.substring(info.lastIndexOf("@") + 1);
                        for (User u : list) {
                            if (u.getAccount().equals(target)) {
                                PrintStream ps = u.getSendOut();
                                ps.println(info);
                                System.out.println("返回客户端:  " + info);
                            }
                        }
                    }
                }
            }
            s.getSendOut().close();
            s.getRcvIn().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
