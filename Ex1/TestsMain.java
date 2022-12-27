public class TestsMain {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("----------- Orel Tests Start -------------");
        new TestOrel();
        System.out.println("----------- Orel Tests Ended -------------\n");
        Thread.sleep(1000);
        System.out.println("----------- Golan Tests Start -------------");
        new TestGolan();
        System.out.println("----------- Golan Tests Ended -------------\n");
        Thread.sleep(1000);
        System.out.println("----------- Rina Tests Start -------------");
        new TestRina();
        System.out.println("----------- Rina Tests Ended -------------\n");
        Thread.sleep(1000);

        System.out.println("----------- All Tested ended -------------");
    }
}