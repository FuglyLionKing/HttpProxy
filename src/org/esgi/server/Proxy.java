package org.esgi.server;

import org.esgi.http.HTTPPrepender;
import org.esgi.http.handlers.HttpRequestHandler;
import org.esgi.http.handlers.ResponseHttpHandler;
import org.interfaces.MyCallBack;
import org.utils.RoundRobiner;
import org.utils.StreamRedirecter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Proxy {

    ServerSocket serverProxy = null;
    RoundRobiner roundRobiner;

    public static void main(String args[]) {
        new Proxy(2000, new RoundRobiner.HttpServerAddress("127.0.0.1", 1234) ).run();
    }

    public Proxy(int port, RoundRobiner.HttpServerAddress... addresses) {

        roundRobiner = new RoundRobiner(addresses);

        try {
            serverProxy = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run(){
        if (null != serverProxy )
        {
            Socket clientConnection = null;

            while (true) {
                try {
                    clientConnection = serverProxy.accept();
                    System.out.println("Nouvelle connexion : " + clientConnection);

                    HttpRequestHandler request = HTTPPrepender.parseRequest(clientConnection.getInputStream(), clientConnection.getRemoteSocketAddress().toString());
                    if(null == request){
                        continue;
                    }

//                    System.out.println(request.asString());
//                    System.out.println("**********************");

                    //TODO add/remove headers depending on config

                    //HTTP server socket
                    Socket serverHttp = roundRobiner.getConnection();//new Socket("127.0.0.1", 1234);


                    serverHttp.getOutputStream().write(request.asString().getBytes());

                    ResponseHttpHandler response = HTTPPrepender.parseResponse(serverHttp.getInputStream(), clientConnection.getOutputStream());

                    System.out.println("Response : ");
                    response.getWriter().write(response.getContent());
                    response.getWriter().flush();


                    //TODO add.remove headers

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != clientConnection)
                        try {
                            clientConnection.close();
                            System.out.println("Connection closed");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        }

    }
}