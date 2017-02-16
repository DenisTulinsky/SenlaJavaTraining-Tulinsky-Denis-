package com.senla.training.serverTools;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.senla.training.DI.DI;
import com.senla.training.interfaces.IFacade;
/**
 * Provides instruments for creating a ServerSocket, establishing the connection with Client,
 * starting the ClientThread.
 * 
 * @author Denis
 *
 */
public class ServerConnector implements IServerConnector {
	
	private final org.apache.log4j.Logger log = Logger.getLogger(ServerConnector.class);
		
		public void init(){
		IFacade facade =   (IFacade) DI.load(IFacade.class); 
		facade.init();
		
			try (ServerSocket server = new ServerSocket(7777, 0,
                    InetAddress.getByName("localhost"))) {

				while (true) {

					Socket socket = server.accept(); 
					ClientThread thread = new ClientThread(socket, facade);
					thread.start();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
}
