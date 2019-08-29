package com.oliverloenning;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    private static InputStream in = null;

    private static int serverPort;

    public static void main(String[] args) throws IOException {
	if(args.length == 2) {
	    serverPort = Integer.parseInt(args[1]);
    } else {
	    serverPort = 1337;
    }

	ServerSocket t = new ServerSocket(serverPort);

	try(Socket serverSocket = t.accept()) {
        System.out.println("Client has been connected!");
        User user = new User();
        PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
        Scanner scanner = new Scanner(serverSocket.getInputStream());
        while(scanner.hasNextLine()) {
            String decision = scanner.nextLine();
            System.out.println(decision);
            if(decision.equals("stop")) {
                serverSocket.close();
                System.out.println("Socket closed");
            }
            if(decision.equals("add")) {
                out.println("How much would you like to add?:");
                try{
                    int balance = Integer.parseInt(scanner.nextLine());
                    user.addBalance(balance);
                    out.println("New balance is: " + user.getBalance());
                } catch(Exception e){
                    out.println("Input must be a integer");
                }
            }
            if(decision.equals("withdraw")) {
                out.println("How much would you like to withdraw?:");
                try{
                    int balance = Integer.parseInt(scanner.nextLine());
                    if(user.getBalance() < balance) {
                        out.println("Withdrawal is too much. Your current balance is: " + user.getBalance() + " and you wanted: " + balance + " in withdrawal");
                    } else {
                        user.removeBalance(balance);
                        out.println("New balance is: " + user.getBalance());
                    }
                } catch(Exception e){
                    out.println("Input must be a integer");
                }
            }
        }
    }
	t.close();
    }
}
