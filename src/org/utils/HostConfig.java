package org.utils;

import org.interfaces.IHeaderModifier;
import org.interfaces.ILoadBalancer;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 02/04/2014
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class HostConfig
{
    public ILoadBalancer loadBalancer;
    public IHeaderModifier incomingModifier;
    public IHeaderModifier outgoingModifier;
}