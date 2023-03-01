import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

public class Worker extends Thread implements WorkerListener {
	private LinkedBlockingQueue<Runnable> queue;
	
	private Runnable task;	
	Connection conn = null;
	int numLongTimeSecond=5000;    
	@Override
	public void register(LinkedBlockingQueue<Runnable> queue, Connection conn) {
		// TODO Auto-generated method stub
		this.queue = queue;	
		this.conn = conn;
	}
	
	public void run() {	        
         
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {  
                    try {
                        queue.wait(); // 요청이 올때까지 대기
                    } catch (InterruptedException e) {
                        System.out.println("InterruptedException : " + e.getMessage());
                    }
                }
                task = (Runnable) queue.poll();
            }

            try {
            	((StressRun)task).setCon(this.conn);
            	Timer();
            	//execute();
                //task.run();
            } catch (RuntimeException e) {
                System.out.println("RuntimeException : " + e.getMessage());
            } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
	
	
	
	public void Timer() throws InterruptedException {
		int nDelay=0;
		int nInterval=1000;
        Timer timer = new Timer(true);        
        timer.schedule(new TimerTask() {
        	int rcnt=0;
            @Override
            public void run() {
            	rcnt = (rcnt + nInterval);
            	if(rcnt < numLongTimeSecond){
            		task.run();
                	System.out.println("task............................." + rcnt);
                }else{                	
                    timer.cancel();
                    System.out.println("task Cancel!!");
                }
            }            
        }, nDelay, nInterval);
        
    }
}
