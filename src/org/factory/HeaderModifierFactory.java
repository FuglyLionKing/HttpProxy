package org.factory;

import org.esgi.config.HeaderLine;
import org.interfaces.IHeaderModifier;
import org.utils.HeaderModifier;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 02/04/2014
 * Time: 15:04
 * To change this template use File | Settings | File Templates.
 */
public class HeaderModifierFactory {

    public static IHeaderModifier get(HeaderLine headLine)
    {
        HashMap<String, String> hashHeader = new HashMap<String, String>();
        for(HashMap<String, String> hash : headLine.add)
            for(Map.Entry<String, String> key : hash.entrySet())
                hashHeader.put(key.getKey(), key.getValue());

        return new HeaderModifier(hashHeader, null);
    }
}
