package org.jccdemo.dsf.javanio.tools;

import java.io.IOException;

import org.jccdemo.dsf.javanio.client.ClientMain;
import org.jccdemo.dsf.javanio.server.ServerMain;

public class TestStart {

	public static void main(String[] args) throws IOException, InterruptedException {
		new Thread(new Runnable() {
			public void run() {
				try {
					ServerMain.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		Thread.sleep(2000);
		new Thread(new Runnable() {
			public void run() {
				try {
					ClientMain.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
	}

}
