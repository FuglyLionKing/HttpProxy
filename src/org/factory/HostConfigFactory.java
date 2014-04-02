package org.factory;

import org.esgi.config.Host;
import org.utils.HostConfig;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 02/04/2014
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
public class HostConfigFactory
{
    public static HostConfig get(Host host)
    {
       HostConfig hc = new HostConfig();
       hc.loadBalancer = LoadBalancerFactory.get(host.lb, host.workers);
       hc.incomingModifier = HeaderModifierFactory.get(host.headers.incoming);
       hc.incomingModifier = HeaderModifierFactory.get(host.headers.outgoing);
       return hc;
    }
}