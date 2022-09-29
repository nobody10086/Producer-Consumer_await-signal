import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    private int max_Storage_Size = 10;  // 仓库最大存储量
    private LinkedList<Object> List = new LinkedList<Object>();// 仓库存储的载体
    private final Lock lock = new ReentrantLock(); //锁
    private final Condition full = lock.newCondition();//仓满的条件变量
    private final Condition empty = lock.newCondition();//仓空的条件变量

    public void producer() {
        //获得锁
        lock.lock();
        while (List.size() + 1 > max_Storage_Size) {
            System.out.println(
                    "The Storage is full." + "The Producer Thread [" + Thread.currentThread().getName() + "] has worked done"
            );
            try {
                full.await();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        List.add(new Object());
        System.out.println(
                "The Producer Thread [" + Thread.currentThread().getName() + "] has added one successfully."
                        + "\tNow the Storage_Size : " + List.size()
        );
        empty.signalAll();
        lock.unlock();
    }

    public void consumer() {
        //获得锁
        lock.lock();
        while (List.size() == 0) {
            System.out.println(
                    "The Storage is empty." + "The Consumer Thread [" + Thread.currentThread().getName() + "] has worked done"
            );
            try {
                empty.await();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        List.remove();
        System.out.println(
                "The Consumer Thread [" + Thread.currentThread().getName() + "] has removed one successfully."
                        + "\tNow the Storage_Size : " + List.size()
        );
        full.signalAll();
        lock.unlock();
    }
}