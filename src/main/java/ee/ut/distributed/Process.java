package ee.ut.distributed;

import java.util.LinkedList;

public class Process {
    private static final LinkedList<Communication> LIST = new LinkedList<>();
    private static final LinkedList<Communication> QUEUE = new LinkedList<>();

    public void addLink() {
        LIST.add(new Communication());
    }

    public void sendRequest(int index) {
        LIST.get(index).setRequest(true);
    }

    public boolean checkRequest() {
        return LIST.stream().anyMatch(Communication::getRequest);
    }

    public void sendReply(int index) {
        LIST.get(index).setReply(true);
    }

    public boolean checkReplies() {
        return LIST.stream().allMatch(Communication::getReply);
    }

    public void enque(int index) {
        QUEUE.addLast(LIST.get(index));
    }

    public void deque() {
        if (QUEUE.isEmpty()) {
            return;
        }
        QUEUE.removeFirst();
    }

    public boolean checkFirst(int index) {
        return LIST.get(index) == QUEUE.getFirst();
    }
}
