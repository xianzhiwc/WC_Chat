package bean;

public class Message {
    public String head;
    public String body;
    public String sign;
    public String target;


    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSign() {
        return sign;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Message mixMs(String sendInfo, String sign,String target,User user) {
        setHead(user.getName() + "|" + user.getIp() + "|" + user.getAccount());
        setBody(sendInfo);
        setSign(sign);
        setTarget(target);
        return this;
    }

}
