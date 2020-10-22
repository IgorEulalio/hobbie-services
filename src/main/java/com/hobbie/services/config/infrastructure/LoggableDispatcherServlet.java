package com.hobbie.services.config.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LoggableDispatcherServlet extends DispatcherServlet implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger("HttpLogger");

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final Map<String, List<Object>> requestsAndResponses = new HashMap<>();

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        // Create a JSON object to store HTTP log information
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("uri", requestWrapper.getRequestURI());
        rootNode.put("clientIp", requestWrapper.getRemoteAddr());
        rootNode.set("requestHeaders", mapper.valueToTree(getRequestHeaders(requestWrapper)));
        String method = requestWrapper.getMethod();
        rootNode.put("method", method);
        try {
            super.doDispatch(requestWrapper, responseWrapper);
        } finally {
            if (method.equals("GET")) {
                rootNode.set("request", mapper.valueToTree(requestWrapper.getParameterMap()));
            } else {
                JsonNode newNode = mapper.readTree(requestWrapper.getContentAsByteArray());
                rootNode.set("request", newNode);
            }

            rootNode.put("status", responseWrapper.getStatus());
            JsonNode newNode = mapper.readTree(responseWrapper.getContentAsByteArray());
            rootNode.set("response", newNode);
            responseWrapper.copyBodyToResponse();

            rootNode.set("responseHeaders", mapper.valueToTree(getResponsetHeaders(responseWrapper)));

            rootNode.set("integrations", mapper.valueToTree(requestsAndResponses));

//            logando request e response da minha app
            logger.info(rootNode.toString());
        }
    }

    private Object getRequestHeaders(HttpServletRequest request) {
        Map<String, Object> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;

    }

    private Object getResponsetHeaders(ContentCachingResponseWrapper response) {
        Map<String, Object> headers = new HashMap<>();
        Collection<String> headerNames = response.getHeaderNames();
        for (String headerName : headerNames) {
            headers.put(headerName, response.getHeader(headerName));
        }
        return headers;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ObjectNode objectRequest = traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        ObjectNode objectResponse = traceResponse(response);

        requestsAndResponses.put(objectRequest.get("uri").toPrettyString(), Arrays.asList(objectRequest, objectResponse));

//        logger.info("{\"Request\": {}\n,\"Response\": {}}", objectRequest, objectResponse);
        return response;
    }

    public ObjectNode traceRequest(HttpRequest request, byte[] body) throws IOException {
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("uri", String.valueOf(request.getURI()));
        rootNode.put("method", String.valueOf(request.getMethod()));
        rootNode.set("headers", mapper.valueToTree(String.valueOf(request.getHeaders())));
        rootNode.put("body", new String(body, "UTF-8"));

        return rootNode;
    }

    public ObjectNode traceResponse(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }

        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("status", String.valueOf(response.getStatusCode()));
        rootNode.put("status_text", String.valueOf(response.getStatusText()));
        rootNode.set("headers", mapper.valueToTree(String.valueOf(response.getHeaders())));
        rootNode.put("body", new String(inputStringBuilder.toString()));

        return rootNode;
    }
}