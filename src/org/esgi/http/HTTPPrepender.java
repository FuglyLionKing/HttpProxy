package org.esgi.http;

import org.esgi.http.handlers.HttpRequestHandler;
import org.esgi.http.handlers.ResponseHttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;

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
        while (0 < (b = reader.read(buffer))) {
            builder.append(buffer, 0, b);
            String bufferEnd = builder.substring(builder.length() - 4, builder.length());
            if (requestEnd.equals(bufferEnd))
                break;
        }

        System.out.println("*********Received********");
        System.out.println(builder.toString());
        System.out.println("**** End of received *****");

        return builder.toString();
    }

    public static HttpRequestHandler parseRequest(InputStream stream, String remoteAdr) throws IOException {

        String request = httpReader(stream);

        return isValidRequest(request) ? new HttpRequestHandler(request, remoteAdr, null) : null;

    }

    public static ResponseHttpHandler parseResponse(InputStream stream, OutputStream out) throws IOException {
        String response = httpReader(stream);

        ResponseHttpHandler handler = new ResponseHttpHandler(out);

        String content = fillHeaderReturnContent(response,handler);

        handler.setContent(content);


        return handler;
    }


    public static String fillHeaderReturnContent(String unParsedResponse, ResponseHttpHandler emptyResponse){
        String[] lines = unParsedResponse.split("\r\n");

        String[] firstLine = lines[0].split(" ");
        String version = firstLine[0];
        String codeCode = firstLine[1];
        String code = firstLine[2];

        emptyResponse.setHttpCode(codeCode + " " +code);
        emptyResponse.setVersion(version);

        int i = 1;
        for(; i < lines.length; i++){
            if("".equals(lines[i]))
                break;
            String[] nameValue = lines[i].split(": ");
            emptyResponse.addHeader(nameValue[0], nameValue[1]);
        }

        StringBuilder content = new StringBuilder();

        for(; i < lines.length; i++){
            content.append(lines[i]);
            content.append("\r\n");
        }

        return content.toString();
    }

    private static boolean isValidRequest(String request) {
        return null != request && !request.isEmpty();
    }
}
