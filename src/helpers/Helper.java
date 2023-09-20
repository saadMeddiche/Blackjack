package helpers;

public class Helper {

    public static Integer randomNumber(Integer from, Integer to) {

        return (int) Math.floor(Math.random() * (to - from + 1) + from);

    }

    public static Integer myRandomNumber(Integer from, Integer to) {

        // for (int i = 0; i < 500; i++) {
        long time = System.currentTimeMillis();

        Double seed = (Double) (time % 0.8555);

        int randomNumber = (int) Math.floor(seed * (to - from + 1) + from);

        return randomNumber;
        // }

    }

    public static void main(String[] args) throws Exception {

    }
}
