package org.esgi.http.interfaces;

import org.interfaces.HasHeader;

import java.io.OutputStream;
import java.io.Writer;

public interface IResponseHttpHandler extends HasHeader {
	
	void flush();
	Writer getWriter();
	OutputStream getOutputStream();
	void addHeader(String key, String value);
	void setHttpCode(String code);
    void addCookie(String name, String value, int duration, String path);

    void setErrorCode();

}
