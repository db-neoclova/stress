import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StressRun implements Runnable {
	
	//Thread thrd;
	@SuppressWarnings("unused")
	private int num;
	Connection conn = null;
	
    public void setCon(Connection conn){
    	this.conn=conn;
    }
    
    public StressRun(int n, Connection conn){
    	this.num=n;
    	this.conn=conn;
    }
    
	@Override
	public void run() {
		try {
        	PreparedStatement pstmt = null;
			String sql = "insert into sbtest1 (k,c,pad) values (?,?,?)";
			//stmt = conn.createStatement();
			//rs = stmt.executeQuery(sql);
			pstmt = conn.prepareStatement(sql);
			int x = (int)(Math.random()*1000000);
			pstmt.setInt(1, x );
	        pstmt.setString(2, String.valueOf( Math.random() ) );
	        pstmt.setString(3, String.valueOf( 1- Math.random() ));
	        int count = pstmt.executeUpdate();
	        if( count == 0 ){
	            System.out.println("데이터 입력 실패");
	        }
	        else{
	            //System.out.println(x);
	        }	        
	        //Thread.sleep(1000);
        } catch (SQLException e) {
			e.printStackTrace();
        }
       
	}

}
