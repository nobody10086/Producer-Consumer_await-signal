public class Consumer implements Runnable{
    private Storage storage;
    public Consumer(Storage storage){
        this.storage = storage;
    }
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(3000);
                storage.consumer();
            }catch (InterruptedException interruptedException){
                interruptedException.printStackTrace();
            }
        }
    }
}
