package org.bremersee.pts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class Controller {

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> readRequest(HttpServletRequest request) {
    final Map<String, Object> headers = new TreeMap<>();
    request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
      List<String> headerValues = new ArrayList<>();
      request.getHeaders(headerName).asIterator().forEachRemaining(headerValues::add);
      headers.put(headerName, headerValues);
    });

    final Map<String, Object> map = new TreeMap<>();
    map.put("authType", request.getAuthType());
    map.put("contextPath", request.getContextPath());
    map.put("headers", headers);
    map.put("localAddr", request.getLocalAddr());
    map.put("method", request.getMethod());
    map.put("pathInfo", request.getPathInfo());
    map.put("pathTranslated", request.getPathTranslated());
    map.put("queryString", request.getQueryString());
    map.put("remoteUser", request.getRemoteUser());
    map.put("requestURI", request.getRequestURI());
    map.put("servletPath", request.getServletPath());
    map.put("trailerFields", request.getTrailerFields());
    map.put("localName", request.getLocalName());
    map.put("localPort", request.getLocalPort());
    map.put("protocol", request.getProtocol());
    map.put("remoteAddr", request.getRemoteAddr());
    map.put("remoteHost", request.getRemoteHost());
    map.put("remotePort", request.getRemotePort());
    map.put("schema", request.getScheme());
    map.put("serverName", request.getServerName());
    map.put("serverPort", request.getServerPort());
    return ResponseEntity.ok(map);
  }

}
