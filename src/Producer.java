public class Producer implements Runnable{
    private Storage storage;
    public Producer(Storage storage){
        this.storage = storage;
    }
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(1000);
                storage.producer();
            }catch (InterruptedException interruptedException){
                interruptedException.printStackTrace();
            }
        }
    }
}