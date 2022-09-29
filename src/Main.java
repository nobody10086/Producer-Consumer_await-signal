public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Thread producer01 = new Thread(new Producer(storage));
        Thread producer02 = new Thread(new Producer(storage));
        Thread producer03 = new Thread(new Producer(storage));

        Thread consumer01 = new Thread(new Consumer(storage));
        Thread consumer02 = new Thread(new Consumer(storage));
        Thread consumer03 = new Thread(new Consumer(storage));

        producer01.setName("producer01");
        producer02.setName("producer02");
        producer03.setName("producer03");
        consumer01.setName("consumer01");
        consumer02.setName("consumer02");
        consumer03.setName("consumer03");

        producer01.start();
        producer02.start();
        producer03.start();
        consumer01.start();
        consumer02.start();
        consumer03.start();
    }
}