import java.sql.Connection;
import java.util.concurrent.LinkedBlockingQueue;

public interface WorkerListener {
	void register(LinkedBlockingQueue<Runnable> queue, Connection conn);
}

