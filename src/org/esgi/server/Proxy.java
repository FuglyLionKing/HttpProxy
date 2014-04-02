package org.esgi.server;

import org.esgi.config.ProxyConfig;
import org.esgi.http.HTTPPrepender;
import org.esgi.http.handlers.HttpRequestHandler;
import org.esgi.http.handlers.ResponseHttpHandler;
import org.factory.HostListFactory;
import org.interfaces.HasHeader;
import org.utils.HostConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Proxy {

    ServerSocket serverProxy = null;
    Map<String, HostConfig> configs;

    public static void main(String args[]) {
        //TODO parse json
        ProxyConfig config = null;

        new Proxy(2000, HostListFactory.getDumy("mon-site.com:2000", "127.0.0.1", 1234)).run();
    }

    public Proxy(int port, Map<String, HostConfig> configs) {

        //roundRobiner = new RoundRobiner(addresses);
        this.configs = configs;

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

                    String host = request.getHostname();
                    HostConfig config = configs.get(host);
                    if(null == config)
                        //TODO see through proxy
                        return;

                    //HTTP server socket
                    Socket serverHttp = config.loadBalancer.getConnection();//new Socket("127.0.0.1", 1234);

                    config.incomingModifier.modify((HasHeader)request);
                    serverHttp.getOutputStream().write(request.asString().getBytes());

                    ResponseHttpHandler response = HTTPPrepender.parseResponse(serverHttp.getInputStream(), clientConnection.getOutputStream());
                    config.outgoingModifier.modify((HasHeader)response);




                    System.out.println("---------ProxResponse----------");
                    response.getWriter().write(new char[0]);
                    response.getOutputStream().write(response.getContent());
                    response.getWriter().flush();
                    System.out.println("-------------------------------");


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