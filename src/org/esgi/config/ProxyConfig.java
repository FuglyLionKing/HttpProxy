package org.esgi.config;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProxyConfig {
	public List<Host> hosts;
	@Override
	public String toString() {

        return "ProxyConfig [hosts=" + hosts + "]";
	}

    public static ProxyConfig getParseConfigFile()
    {
        ObjectMapper mapper = new ObjectMapper();
        ProxyConfig config = null;
        try{
            config = mapper.readValue(new File("config.js"), ProxyConfig.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return config;
    }
	
}
