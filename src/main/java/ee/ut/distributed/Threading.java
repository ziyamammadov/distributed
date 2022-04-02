package ee.ut.distributed;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
class Threading implements Runnable {
    private final String name;
    private final int index;
    private final Process process = new Process();
    private final Thread thread;
    private State state = State.DO_NOT_WANT;

    Threading(int index) {
        this.index = index;
        this.name = "Thread-" + index;
        log.info("Initializing: {}", name);
        this.thread = new Thread(this, name);
        process.addLink();
    }

    public void setState(State state) {
        log.info("{}: changing state from {} to {}", this.name, this.state, state);
        this.state = state;
    }

    @SneakyThrows
    public void run() {
        log.info("Running: {}", name);
        process.sendRequest(index);
        process.enque(index);
        while (!process.checkReplies()) {
            if (process.checkRequest()) {
                process.sendReply(index);
            }
        }
        TimeUnit.MILLISECONDS.sleep(150);
        setState(State.WANTED);
        while (!process.checkFirst(index)) {
            log.info("Waiting: {}", this.name);
            TimeUnit.MILLISECONDS.sleep(150);
        }
        enterCS();
        process.deque();
        process.sendReply(index);
        setState(State.DO_NOT_WANT);
        log.info("Finishing: {}", this.name);
    }

    public void start() {
        log.info("Starting a thread: {}", this.name);
        this.thread.start();
    }

    @SneakyThrows
    public void enterCS() {
        setState(State.HELD);
        log.info("{} entering to CS", this.name);
        TimeUnit.SECONDS.sleep(1);
    }
}
