package org.esgi.config;

import java.util.List;

public class Host {
	public String host;
    public String lb;
    public List<String> workers;
    public Header headers;

	@Override
    public String toString() {
            return "Host [Host=" + host + " lb="+lb+" workers="+workers+" head="+headers.toString()+"]";
        }
}
