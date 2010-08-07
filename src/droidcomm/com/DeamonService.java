package droidcomm.com;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class DeamonService extends Service {
	private NotificationManager noficationMgr;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		noficationMgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		//Notification nofification = new Notification(R.drawable.ic_menu_day, "Hello", System.currentTimeMillis());
		
		Thread thr = new Thread(null, new Deamon(), "Backgrould Deamon");
		thr.start();
	}
	
	class Deamon implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int l4port = 5757;
			try {
				ServerSocket ServerSocket = new ServerSocket(l4port);
				Socket socket = null;
				do{
					socket = ServerSocket.accept();
					InetAddress remoteIP = socket.getInetAddress();
					InputStream is = socket.getInputStream();
					Log.v("Deamon", remoteIP.toString());
					Log.v("Deamon", is.toString());
				}while(socket!=null);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
