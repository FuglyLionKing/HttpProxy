package org.esgi.http;

import org.esgi.http.handlers.HttpRequestHandler;
import org.esgi.http.handlers.ResponseHttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: FuglyLionKing
 * Date: 02/04/14
 * Time: 10:00
 * To change this template use File | Settings | File Templates.
 */
public class HTTPPrepender {


    private static String httpReader(InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(stream);
        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        String requestEnd = "\r\n\r\n";

        int b;
        while (-1 != (b = reader.read(buffer))) {
            builder.append(buffer, 0, b);
            String bufferEnd = builder.substring(builder.length() - 4, builder.length());
            if (requestEnd.equals(bufferEnd))
                break;
        }

        return builder.toString();
    }

    public static HttpRequestHandler parseRequest(InputStream stream, String remoteAdr) throws IOException {

        String request = httpReader(stream);

        return isValidRequest(request) ? new HttpRequestHandler(request, remoteAdr, null) : null;

    }

    public static ResponseHttpHandler parseResponse(InputStream stream) throws IOException {
        String reponse = httpReader(stream);

        return null;
    }

    private static boolean isValidRequest(String request) {
        return null != request && !request.isEmpty();
    }
}
