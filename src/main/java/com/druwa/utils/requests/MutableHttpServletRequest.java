package com.druwa.utils.requests;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * Class to make servlet request mutable
 * - supports custom header manipulation
 *
 * Usage Example (Filter)
 * - Note: Spring Security filter is always first to execute
 *         Use application property(spring security filter padding): spring.security.filter.order=100
 * @Component
 * @Order(50)
 * public class PreJwtAuthHeaderFilter implements Filter {
 *     @Override
 *     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 *         // custom headers
 *         HttpServletRequest req = (HttpServletRequest) request;
 *         MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);
 *         mutableRequest.putHeader(Constants.CustomHeaders.PRE_JWT_FILTER_HEADER, "pre jwt");
 *         chain.doFilter(mutableRequest, response);
 *     }
 * }
 */
public final class MutableHttpServletRequest extends HttpServletRequestWrapper {
    // holds custom header and value mapping
    private final Map<String, String> customHeaders;

    public MutableHttpServletRequest(HttpServletRequest request) {
        super(request);
        this.customHeaders = new HashMap<String, String>();
    }

    public void putHeader(String name, String value) {
        this.customHeaders.put(name, value);
    }

    public String getHeader(String name) {
        // check the custom headers first
        String headerValue = customHeaders.get(name);

        if (headerValue != null) {
            return headerValue;
        }
        // else return from into the original wrapped object
        return ((HttpServletRequest) getRequest()).getHeader(name);
    }

    public Enumeration<String> getHeaderNames() {
        // create a set of the custom header names
        Set<String> set = new HashSet<String>(customHeaders.keySet());

        // now add the headers from the wrapped request object
        @SuppressWarnings("unchecked")
        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
        while (e.hasMoreElements()) {
            // add the names of the request headers into the list
            String n = e.nextElement();
            set.add(n);
        }

        // create an enumeration from the set and return
        return Collections.enumeration(set);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        Set<String> set = new HashSet<>();
        Optional.ofNullable(customHeaders.get(name)).ifPresent(h -> set.add(h));
        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaders(name);
        while (e.hasMoreElements()) {
            // add the names of the request headers into the list
            String n = e.nextElement();
            set.add(n);
        }
        Optional.ofNullable(customHeaders.get(name)).ifPresent(h -> set.add(h));
        return Collections.enumeration(set);
    }
}