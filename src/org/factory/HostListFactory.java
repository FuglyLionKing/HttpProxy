package org.factory;

import org.esgi.config.ProxyConfig;
import org.utils.HeaderModifier;
import org.utils.HostConfig;
import org.utils.RoundRobiner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 02/04/2014
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class HostListFactory
{
    public static  Map<String, HostConfig> get(ProxyConfig config)
    {
        return null;
    }


    public static HashMap<String , HostConfig> getDumy(String hostname, String worker, int workerPort){

        HashMap<String , HostConfig> map = new HashMap<String, HostConfig>();

        HostConfig config = new HostConfig();
        config.loadBalancer = new RoundRobiner(new RoundRobiner.HttpServerAddress(worker, workerPort));

        HashMap<String, String> toAdd = new HashMap<String, String>();
        toAdd.put("I-Catched-You", "Ahahah");

        config.incomingModifier = new HeaderModifier(toAdd,null);
        config.outgoingModifier = new HeaderModifier(toAdd,null);

        map.put(hostname,config);

        return map;
    }
}
