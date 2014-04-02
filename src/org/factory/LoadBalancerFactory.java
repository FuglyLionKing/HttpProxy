package org.factory;

import org.interfaces.ILoadBalancer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 02/04/2014
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
public class LoadBalancerFactory{

    ILoadBalancer get(String method, List<String> workers)
    {
        return null;
    }
}
