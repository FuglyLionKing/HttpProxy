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
        //new Proxy(2000, HostListFactory.getDumy("mon-site.com:2000", "127.0.0.1", 1234)).run();
        new Proxy(2000, HostListFactory.get(ProxyConfig.getParseConfigFile())).run();
    }

    public Proxy(int port, Map<String, HostConfig> configs) {

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

                    String host = request.getHostname();
                    HostConfig config = configs.get(host);

                    //We don't handle this host, 404
                    if(null == config){
                        ResponseHttpHandler fourOfour = new ResponseHttpHandler(clientConnection.getOutputStream());
                        fourOfour.setVersion("HTTP/1.1");
                        fourOfour.setHttpCode("404 Not Found");
                        fourOfour.addHeader("Content-Type", "text/html");

                        fourOfour.getWriter().write("<body>404</body>");
                        fourOfour.flush();
                        continue;
                    }

                    Socket serverHttp = config.loadBalancer.getConnection();

                    config.incomingModifier.modify(request);
                    serverHttp.getOutputStream().write(request.asString().getBytes());
                    serverHttp.getOutputStream().flush();

                    ResponseHttpHandler response = HTTPPrepender.parseResponse(serverHttp.getInputStream(), clientConnection.getOutputStream());
                    config.outgoingModifier.modify(response);

                    response.getWriter().write(new char[0]);
                    response.getOutputStream().write(response.getContent());
                    response.getWriter().flush();

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