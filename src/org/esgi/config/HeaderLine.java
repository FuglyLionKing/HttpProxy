package org.esgi.config;

import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 02/04/2014
 * Time: 12:18
 * To change this template use File | Settings | File Templates.
 */
public class HeaderLine
{
    public List<HashMap<String, String>> add;

    @Override
    public String toString(){

        return generate();
    }

    public String generate()
    {
        StringBuilder str=new StringBuilder();
        for(int i=0;i<add.size();i++) {
            HashMap<String, String> hash = add.get(i);
            for (String key : hash.keySet() ) {
                str.append("["+key+"= ");
                str.append(hash.get(key)+"]");
            }
        }
        return str.toString();
    }
}
