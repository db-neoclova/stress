import java.sql.SQLException;


//@SpringBootApplication
public class NeoStress  {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException  {
		//SpringApplication.run(NeoStress.class, args);
		/*
		[{
			"mysql-host": "192.168.14.14",
			"mysql-port": "3306",
			"mysql-user":"maxscale",
			"mysql-password":"maxscale",
			"mysql-db":"sysbench",
			"threads":"10",
			"times":"5000"
		}]
		 */
		
		
		System.out.println("NeoClova Stress Test Start!!");
		int numThreads=10;
		int numLongTimeSecond=5000;
        
		StressPool pool = new StressPool(numThreads);
		for (int i = 0; i < numThreads; i++) {
        	StressRun task = new StressRun(i, null);
            pool.execute(task);
            //System.out.println("----------"+ i);
        }
        Thread.sleep(numLongTimeSecond); 
        System.out.println("NeoClova Stress Test End!!");
        return;
	}

}





