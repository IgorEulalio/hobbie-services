package com.hobbie.services.config.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class LoggableDispatcherServlet extends DispatcherServlet {

    private static final Logger logger = LoggerFactory.getLogger("HttpLogger");

    private static final ObjectMapper mapper = new ObjectMapper();

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

}