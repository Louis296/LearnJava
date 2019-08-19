package Internet;
import java.io.*;
import java.net.*;

public class DailyAdviceClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DailyAdviceClient client=new DailyAdviceClient();
		client.go();
	}
	
	public void go() {
		try {
			Socket s=new Socket("127.0.0.1",4242);
			
			InputStreamReader streamReader=new InputStreamReader(s.getInputStream());
			BufferedReader reader=new BufferedReader(streamReader);
			
			String advice=reader.readLine();
			System.out.println("Today you should:"+advice);
			
			reader.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
