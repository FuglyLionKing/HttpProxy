package org.utils;

import org.interfaces.ILoadBalancer;

import java.io.IOException;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: FuglyLionKing
 * Date: 02/04/14
 * Time: 09:22
 * To change this template use File | Settings | File Templates.
 */
public class RoundRobiner implements ILoadBalancer{

    public static class HttpServerAddress {
        String adress;
        int port;

        public HttpServerAddress(String adress, int port) {
            this.adress = adress;
            this.port = port;
        }
    }


    HttpServerAddress[] servers;
    private int connectionCounter = 0;

    public RoundRobiner(HttpServerAddress... servers) {
        this.servers = servers;
    }

    public Socket getConnection() {
        int index = connectionCounter++ % servers.length;

        try {
            return new Socket(servers[index].adress, servers[index].port);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
