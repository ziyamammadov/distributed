package ee.ut.distributed;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

@Slf4j
public class DistributedApplication {

    public static void main(String[] args) {
        String input;
        int amount;
        boolean isExecuting = true;
        List<Threading> threadingList = new ArrayList<>();

        Scanner scan = new Scanner(System.in);
        log.info("Enter count of threads:");

        while (isExecuting) {
            input = scan.next();
            try {
                amount = Integer.parseInt(input);
                IntStream.range(0, amount).mapToObj(Threading::new).forEach(threadingList::add);
                isExecuting = false;
            } catch (NumberFormatException e) {
                log.error("Enter an integer:");
            }
        }
        scan.close();
        threadingList.forEach(Threading::start);
    }
}
