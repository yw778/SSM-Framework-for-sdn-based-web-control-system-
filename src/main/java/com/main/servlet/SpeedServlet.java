package com.main.servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.print.DocFlavor.URL;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.eclipse.jetty.util.ajax.JSON;
import org.eclipse.jetty.websocket.WebSocket;

import com.main.model.ONU;
import com.main.service.ONUService;


public class SpeedServlet extends org.eclipse.jetty.websocket.WebSocketServlet {
	private final Set<AlarmWebSocket> clients;

	public SpeedServlet() {
		

		clients = new CopyOnWriteArraySet<AlarmWebSocket>();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {	
				sendMessage("alarm.add", loadDatas());
			}
		}, 0, 1000);
	}
	
	public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
		
		return new AlarmWebSocket();
	}
    
	public void sendMessage(String action, String message) {
		for(AlarmWebSocket client : clients){
			sendMessage(client, action, message);
		}
	}
	
	private void sendMessage(AlarmWebSocket client, String action, String message){
		try {
			if(message == null || message.isEmpty()){
				message = "\"\"";
			}
			if(action != null){
				message = "{\"action\":\"" + action + "\", \"data\":" + message + "}";
			}
			client.connection.sendMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	private String loadDatas(){
		List<Integer> speeds=new ArrayList<Integer>();
		
		java.net.URL url = SpeedServlet.class.getClassLoader().getResource("speed.txt");
		String fileName = url.getPath();    
		System.out.println("---"+fileName);
		
		


	        // This will reference one line at a time
	        String line = null;

	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(fileName);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);

	            line = bufferedReader.readLine();
	            System.out.println("in servlet\n"+line);
	            String[] speed=line.split(",");
	            System.out.println("in servlet\n"+speed[0]);
	            speeds.add(Integer.parseInt(speed[1]));
	            speeds.add(Integer.parseInt(speed[2]));
	            speeds.add(Integer.parseInt(speed[3]));
	            speeds.add(Integer.parseInt(speed[4]));
	               

	            // Always close files.
	            bufferedReader.close();     
	            fileReader.close();
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                fileName + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + fileName + "'");                  
	       
	        }
			return JSONArray.fromObject(speeds).toString();

		
	}
	
	
    class AlarmWebSocket implements WebSocket.OnTextMessage
    {
    	WebSocket.Connection connection;
		public void onOpen(Connection connect) {
			this.connection = connect;
			clients.add(this);
			sendMessage(this, "reload", loadDatas());
		}
		public void onClose(int code, String message) {
			clients.remove(this);
		}
		public void onMessage(String message) {
			
		}
    }
	
}
