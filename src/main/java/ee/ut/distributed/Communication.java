package ee.ut.distributed;

public class Communication {
    private boolean request = false;
    private boolean reply = false;

    public boolean getReply() {
        return reply;
    }

    public void setReply(boolean reply) {
        this.reply = reply;
    }

    public boolean getRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }
}
