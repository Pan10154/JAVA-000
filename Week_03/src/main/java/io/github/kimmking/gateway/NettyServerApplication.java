package io.github.kimmking.gateway;


import io.github.kimmking.gateway.inbound.HttpInboundServer;

import java.lang.reflect.Array;
import java.util.*;

public class NettyServerApplication {
    
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) {
//        String proxyServer = System.getProperty("proxyServer","http://localhost:8088");
        String proxyPort = System.getProperty("proxyPort","8888");
        
          //  http://localhost:8888/api/hello  ==> gateway API
          //  http://localhost:8801/api/hello  ==> backend service
        Map<String, String> proxyServerMap = new HashMap<>();
        ArrayList<String> uriList = new ArrayList<>();
        uriList.add("http://localhost:8801");
        uriList.add("http://localhost:8802");
        uriList.add("http://localhost:8803");
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
//        HttpInboundServer server = new HttpInboundServer(port, proxyServer);
        HttpInboundServer server = new HttpInboundServer(port, uriList);
//        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + proxyServer);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port );
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
