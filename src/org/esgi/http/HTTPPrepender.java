package org.esgi.http;

import org.esgi.http.handlers.HttpRequestHandler;
import org.esgi.http.handlers.ResponseHttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: FuglyLionKing
 * Date: 02/04/14
 * Time: 10:00
 * To change this template use File | Settings | File Templates.
 */
public class HTTPPrepender {

    private static class Request {
        String header;
        byte[] content;

        private Request(String header, byte[] content) {
            this.header = header;
            this.content = content;
        }
    }

    private static byte[] unbox(List<Byte> list) {
        byte[] ans = new byte[list.size()];
        int i = 0;
        for (Byte b : list) {
            ans[i++] = b.byteValue();
        }

        return ans;
    }

    private static byte[] readContent(InputStream stream, String stopOn) throws IOException {
        byte[] pending = new byte[stopOn.length()];
        int i;
        int stopIndex = 0;

        ArrayList<Byte> bytes = new ArrayList<Byte>();

        while (-1 != (i = stream.read())) {
            //if it may be the stop, don't add it to the header's byte and add to pending bytes
            if (stopOn.charAt(stopIndex) == (char) i) {
                pending[stopIndex] = (byte) i;
                stopIndex++;

                if (stopIndex == stopOn.length())
                    break;
                else
                    continue;
            } else if (0 != stopIndex) {
                //put back in the headers the pending bytes if it was a false alert
                for (int j = 0; j < stopIndex; j++) {
                    bytes.add(pending[j]);
                }
                stopIndex = 0;
            }

            bytes.add((byte) i);
        }

        return unbox(bytes);
    }

    private static String readHeader(InputStream stream, String stopOn) throws IOException {
        byte[] pending = new byte[stopOn.length()];
        int i;
        int stopIndex = 0;

        ArrayList<Byte> bytes = new ArrayList<Byte>();

        while (-1 != (i = stream.read())) {
            //if it may be the stop, don't add it to the header's byte and add to pending bytes
            if (stopOn.charAt(stopIndex) == (char) i) {
                pending[stopIndex] = (byte) i;
                stopIndex++;
                if (stopIndex == stopOn.length())
                    break;
                else
                    continue;
            } else if (0 != stopIndex) {
                //put back in the headers the pending bytes if it was a false alert
                for (int j = 0; j < stopIndex; j++) {
                    bytes.add(pending[j]);
                }
                stopIndex = 0;
            }

            bytes.add((byte) i);
        }

        return new String(unbox(bytes));
    }


//    private static String Request(InputStream stream) throws IOException {
//        StringBuilder builder = new StringBuilder();
//        InputStreamReader reader = new InputStreamReader(stream);
//        int bufferSize = 1024;
//        char[] buffer = new char[bufferSize];
//        String requestEnd = "\r\n\r\n";
//
//        int b;
//        while (0 < (b = reader.read(buffer))) {
//            builder.append(buffer, 0, b);
//            String bufferEnd = builder.substring(builder.length() - 4, builder.length());
//            if (requestEnd.equals(bufferEnd))
//                break;
//        }
//
//        System.out.println("*********Received********");
//        System.out.println(builder.toString());
//        System.out.println("**** End of received *****");
//
//        return builder.toString();
//    }

    public static HttpRequestHandler parseRequest(InputStream stream, String remoteAdr) throws IOException {

        String header = readHeader(stream, "\r\n\r\n");

        return isValidRequest(header) ? new HttpRequestHandler(header, remoteAdr, null) : null;

    }

    public static ResponseHttpHandler parseResponse(InputStream stream, OutputStream out) throws IOException {
        String header = readHeader(stream, "\r\n\r\n");
        byte[] content = readContent(stream, "\r\n\r\n");

        ResponseHttpHandler handler = new ResponseHttpHandler(out);

        fillHeader(header, handler);

        handler.setContent(content);


        return handler;
    }


    public static void fillHeader(String unParsedResponse, ResponseHttpHandler emptyResponse) {
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

    }

    private static boolean isValidRequest(String request) {
        return null != request && !request.isEmpty();
    }
}
