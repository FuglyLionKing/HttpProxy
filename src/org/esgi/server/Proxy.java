package org.esgi.server;

import org.codehaus.jackson.map.ObjectMapper;
import org.esgi.config.ProxyConfig;
import org.utils.RoundRobiner;

import java.io.File;
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
                    ObjectMapper objectMapper = new ObjectMapper();
                    ProxyConfig conf = new ProxyConfig();
                    conf = objectMapper.readValue(new File("config.js"), ProxyConfig.class);
                    System.out.println(conf.toString());

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