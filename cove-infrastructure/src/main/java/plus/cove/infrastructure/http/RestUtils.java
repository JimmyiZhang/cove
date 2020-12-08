package plus.cove.infrastructure.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import plus.cove.infrastructure.exception.BusinessException;
import plus.cove.infrastructure.exception.NetworkError;
import plus.cove.infrastructure.json.UniteJsonConfig;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Rest工具类
 *
 * @author jimmy.zhang
 * @since 1.1
 */
@Slf4j
@Component
public class RestUtils {
    @Autowired
    UniteHttpConfig httpConfig;
    @Autowired
    UniteJsonConfig jsonConfig;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 初始化
     */
    @Bean
    public RestTemplate init(RestTemplateBuilder builder) {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(jsonConfig.getJsonMapper());

        return builder.messageConverters(jsonConverter)
                .setConnectTimeout(httpConfig.getConnectTimeout())
                .setReadTimeout(httpConfig.getReadTimeout())
                .build();
    }

    /**
     * 获取uri参数
     */
    private Object[] getVariable(Object... uriVariables) {
        if (uriVariables == null) {
            return new Object[0];
        } else {
            return uriVariables;
        }
    }

    /**
     * 获取http头
     */
    private HttpHeaders getHeaders(Map<String, String> requestHeaders) {
        HttpHeaders headers = new HttpHeaders();
        if (requestHeaders != null) {
            for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        return headers;
    }

    /**
     * get获取对象
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public <T> T getObject(String requestUri,
                           Map<String, String> requestHeaders,
                           Class<T> responseType) {
        URI uri = UriComponentsBuilder.fromUriString(requestUri)
                .build(new Object[0]);
        HttpHeaders headers = getHeaders(requestHeaders);

        RequestEntity<Void> request = RequestEntity.get(uri)
                .headers(headers)
                .build();

        try {
            ResponseEntity<T> response = restTemplate.exchange(request, responseType);
            return response.getBody();
        } catch (RestClientException ex) {
            throw new BusinessException(NetworkError.REST_GET_ERROR, ex);
        }
    }

    /**
     * get获取列表
     *
     * @param requestUri
     * @param responseType
     * @param <T>
     * @return
     */
    public <T> List<T> getList(String requestUri,
                               Map<String, String> requestHeaders,
                               Class<T> responseType) {
        URI uri = UriComponentsBuilder.fromUriString(requestUri)
                .build(new Object[0]);
        HttpHeaders headers = getHeaders(requestHeaders);

        RequestEntity<Void> request = RequestEntity.get(uri)
                .headers(headers)
                .build();
        ParameterizedTypeReference<List<T>> paraType = ParameterizedTypeReference.forType(responseType);

        try {
            ResponseEntity<List<T>> response = restTemplate.exchange(request, paraType);
            return response.getBody();
        } catch (RestClientException ex) {
            throw new BusinessException(NetworkError.REST_GET_ERROR, ex);
        }
    }

    /**
     * post对象
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public <T> T postObject(String requestUri,
                            T requestBody,
                            Map<String, String> requestHeaders,
                            Class<T> responseType) {
        URI uri = UriComponentsBuilder.fromUriString(requestUri)
                .build(new Object[0]);
        HttpHeaders headers = getHeaders(requestHeaders);

        RequestEntity<T> request = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .body(requestBody);

        try {
            ResponseEntity<T> response = restTemplate.exchange(request, responseType);
            return response.getBody();
        } catch (RestClientException ex) {
            throw new BusinessException(NetworkError.REST_POST_ERROR, ex);
        }
    }

    /**
     * put对象
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public <T> T putObject(String requestUri,
                           T requestBody,
                           Map<String, String> requestHeaders,
                           Class<T> responseType) {
        URI uri = UriComponentsBuilder.fromUriString(requestUri)
                .build(new Object[0]);
        HttpHeaders headers = getHeaders(requestHeaders);

        RequestEntity<T> request = RequestEntity.put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .body(requestBody);

        try {
            ResponseEntity<T> response = restTemplate.exchange(request, responseType);
            return response.getBody();
        } catch (RestClientException ex) {
            throw new BusinessException(NetworkError.REST_POST_ERROR, ex);
        }
    }
}
