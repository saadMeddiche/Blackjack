package tests;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.out.print("Existing Text ");

        Thread.sleep(2000); 

        System.out.print("\r");

        System.out.print("New Text");
    }
}
