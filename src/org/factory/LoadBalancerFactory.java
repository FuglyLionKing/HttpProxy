package org.factory;

import org.interfaces.ILoadBalancer;
import org.utils.RoundRobiner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 02/04/2014
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
public class LoadBalancerFactory{

    public static  ILoadBalancer get(String method, List<String> workers)
    {

        if("roundrobin".equals(method.toLowerCase())){
            return getRoundRobiner(workers);
        }

        return null;
    }

    private static RoundRobiner getRoundRobiner(List<String> workers){
        RoundRobiner.HttpServerAddress[] adresses = new RoundRobiner.HttpServerAddress[workers.size()];

        int i = 0;
        for(String worker : workers){
            String[] splitted = worker.split(":");
            adresses[i++] = new RoundRobiner.HttpServerAddress(splitted[0], 1 < splitted.length ? new Integer(splitted[1]) : 80);
        }

        return new RoundRobiner(adresses);
    }
}
