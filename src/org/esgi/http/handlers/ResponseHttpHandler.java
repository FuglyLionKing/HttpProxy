package org.esgi.http.handlers;

import org.esgi.http.enums.HTTP_CODES;
import org.esgi.http.interfaces.IResponseHttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created with IntelliJ IDEA.
 * User: Voodoo
 * Date: 31/03/14
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */
public class ResponseHttpHandler implements IResponseHttpHandler {
    private HTTP_CODES code;


    private AutoHeaderWriter writer;
    private OutputStream stream;
    private StringBuilder header = new StringBuilder();
    private int length;
    private String contentType;

    public ResponseHttpHandler(OutputStream stream) {
        this.writer = new AutoHeaderWriter(this.stream = stream);
    }

    @Override
    public void flush() {
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Writer getWriter() {
        return writer;
    }

    @Override
    public OutputStream getOutputStream() {
        return stream;
    }

    @Override
    public void addHeader(String key, String value) {
        header.append(String.format("%s: %s\r\n", key, value));
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public void addCookie(String name, String value, int duration, String path) {
        StringBuilder formattedValue = new StringBuilder();
        formattedValue.append(String.format("%s=%s", name,value));
        if (null != path)
            formattedValue.append(" ;  Path=" + path);
        formattedValue.append("; Max-Age=" + duration);

        addHeader("Set-Cookie", formattedValue.toString());
    }

    @Override
    public void setHttpCode(HTTP_CODES code) {
        this.code = code;
    }

    @Override
    public void setErrorCode() {
        setHttpCode(HTTP_CODES.NOT_FOUND);
    }

    @Override
    public void setContentLength(int lenght) {
        this.length = lenght;
    }


    //
    //
    //
    //
    //
    //
    //      Embedded class
    //
    //
    //
    //
    //
    //


    private class AutoHeaderWriter extends OutputStreamWriter {
        boolean hasWrite = false;

        public AutoHeaderWriter(OutputStream out) {
            super(out);
        }

        private void writeHeader() throws IOException {
            if (hasWrite)
                return;

            hasWrite = true;
            super.write(String.format("HTTP/1.1 %s\r\n" +
                    "Content-Type: %s\r\n" +
                    "Content-Length: %d\r\n", code.toString(), contentType, length));

            super.write(header.toString());
            super.write("\r\n");
        }

        @Override
        public void write(int c) throws IOException {
            writeHeader();
            super.write(c);
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            writeHeader();
            super.write(cbuf, off, len);
        }

        @Override
        public void write(String str, int off, int len) throws IOException {
            writeHeader();
            super.write(str, off, len);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void write(char[] cbuf) throws IOException {
            writeHeader();
            super.write(cbuf);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void write(String str) throws IOException {
            writeHeader();
            super.write(str);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public Writer append(CharSequence csq) throws IOException {
            writeHeader();
            return super.append(csq);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public Writer append(CharSequence csq, int start, int end) throws IOException {
            writeHeader();
            return super.append(csq, start, end);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public Writer append(char c) throws IOException {
            writeHeader();
            return super.append(c);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }
}
