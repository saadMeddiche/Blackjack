package test;

public class test {
    public static void main(String[] args) throws Exception {
        Integer[] numbers = { 1, 2, 3, 4, 5, 6 };

        for (Integer number : numbers) {
            System.err.println(number);
        }
        System.out.println("=================");
        test(numbers);

        for (Integer number : numbers) {
            System.err.println(number);
        }
    }

    public static void test(Integer[] numbers) {

        for (int i = 0; i < numbers.length; i++) {

            numbers[i] = numbers[i] - 1;
        }
    }
}
