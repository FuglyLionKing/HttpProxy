package org.utils;

import org.interfaces.HasHeader;
import org.interfaces.IHeaderModifier;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: FuglyLionKing
 * Date: 02/04/14
 * Time: 16:38
 * To change this template use File | Settings | File Templates.
 */
public class HeaderModifier implements IHeaderModifier {

    Map<String, String> toAdd;
    List<String> toRem;

    public HeaderModifier(Map<String, String> toAdd, List<String> toRem) {
        this.toAdd = toAdd;
        this.toRem = toRem;
    }

    @Override
    public void modify(HasHeader header) {
        if(null == header)
            return;

        if(null != toRem){
            for(String rem : toRem)
                header.removeHeader(rem);
        }

        if(null != toAdd){
            for(Map.Entry<String,String> add : toAdd.entrySet()){
                header.addHeader(add.getKey(),add.getValue());
            }
        }
    }
}
