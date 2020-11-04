package io.github.kimmking.gateway.outbound.myhandler;

import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.HttpEndpointRouterImpl;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static org.apache.http.HttpHeaders.CONNECTION;

public class HttpOutboundHandler {

    private HttpEndpointRouter httpEndpointRouter;
    private List<String> hostList;
    public HttpOutboundHandler(List<String> hostList){
        this.hostList = hostList;
        httpEndpointRouter = new HttpEndpointRouterImpl();
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
        String url = httpEndpointRouter.route(hostList)+uri;
        handleSend(fullRequest ,ctx ,url);
    }

    private void handleSend(FullHttpRequest fullRequest, ChannelHandlerContext ctx ,String url) {
        FullHttpResponse response = null;
        HttpHeaders headers = fullRequest.headers();
        try{
            String result = getString(url ,headers);
            response =  new DefaultFullHttpResponse(HTTP_1_1, OK,Unpooled.wrappedBuffer(result.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            ctx.write(response);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }
    public static String getString(String url ,HttpHeaders headers) {
        HttpGet method = new HttpGet(url);
        //将请求头赋值到http请求 ,发起请求
        List<Map.Entry<String, String>> entryList = headers.entries();
        for (Map.Entry<String, String> entry : entryList) {
            method.setHeader(entry.getKey() ,entry.getValue());
        }
       //发送请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            HttpResponse response = httpClient.execute(method);
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer resBuffer = new StringBuffer();
            String resTemp = "";
            while((resTemp = br.readLine()) != null){
                resBuffer.append(resTemp);
            }
            result = resBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
