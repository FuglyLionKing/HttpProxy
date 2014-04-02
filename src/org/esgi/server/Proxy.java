package org.esgi.server;

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
            try
            {
                while (true)
                {
                    final Socket proxyConnection = serverProxy.accept();
                    System.out.println("Nouvelle connexion : "+proxyConnection);

                    //HTTP server socket
                    final Socket serverHttp = roundRobiner.getConnection();//new Socket("127.0.0.1", 1234);


                }
            }
            catch (Exception ex)
            {
                System.err.println("Une erreur est survenue : " + ex);
            }
        }

    }
}