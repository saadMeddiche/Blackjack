package view;

public class Animation {

    public static void waitingAnimation(long time) throws InterruptedException {

        String[] symbols = { "|", "/", "-", "\\" };
        int symbolIndex = 0;

        long startTime = System.currentTimeMillis();
        long periodBetweenPastAndFuture = 0;

        while (periodBetweenPastAndFuture < time) {

            System.out.print("\r" + symbols[symbolIndex]);

            symbolIndex = (symbolIndex + 1) % symbols.length;

            Thread.sleep(250);

            periodBetweenPastAndFuture = System.currentTimeMillis() - startTime;
        }

        System.out.println();
    }
}
