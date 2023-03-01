import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

public class StressPool {

	
    
	@SuppressWarnings("unused")
	private int nThread;
	private final Worker[] workers;
    private final LinkedBlockingQueue<Runnable> taskQueue;
 
    public StressPool (int nThread) throws ClassNotFoundException, SQLException {
        this.nThread = nThread;
        taskQueue = new LinkedBlockingQueue<Runnable>();
        workers = new Worker[nThread];
 
        for (int i = 0; i < nThread; i++) {
        	Connection conn = null;
            Class.forName("org.mariadb.jdbc.Driver");			
    		String url = "jdbc:mariadb://192.168.14.14/sysbench";
    		conn = DriverManager.getConnection(url, "maxscale", "maxscale");
    		
        	workers[i] = new Worker();
            workers[i].start()	;
            workers[i].register(taskQueue,  conn);
        }
        //Timer();
    }
 
    public void execute(Runnable task) {        
        
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
        }
    }
 
}
