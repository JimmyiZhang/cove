package plus.cove.infrastructure.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
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
     * get获取对象
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public <T> T getObject(String uriString, Class<T> responseType, Object... uriVariables) {
        URI uri = UriComponentsBuilder.fromUriString(uriString)
                .build(uriVariables);
        RequestEntity<Void> request = RequestEntity.get(uri)
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
     * @param uriString
     * @param responseType
     * @param uriVariables
     * @param <T>
     * @return
     */
    public <T> List<T> getList(String uriString, Class<T> responseType, Object... uriVariables) {
        URI uri = UriComponentsBuilder.fromUriString(uriString)
                .build(uriVariables);
        RequestEntity<Void> request = RequestEntity.get(uri)
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
    public <T> T postObject(String uriString, T object, Class<T> responseType, Object... uriVariables) {
        URI uri = UriComponentsBuilder.fromUriString(uriString)
                .build(uriVariables);
        RequestEntity<T> request = RequestEntity.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(object);

        try {
            ResponseEntity<T> response = restTemplate.exchange(request, responseType);
            return response.getBody();
        } catch (RestClientException ex) {
            throw new BusinessException(NetworkError.REST_POST_ERROR, ex);
        }
    }
}
