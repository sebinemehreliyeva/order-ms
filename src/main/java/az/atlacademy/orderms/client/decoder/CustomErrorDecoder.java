package az.atlacademy.orderms.client.decoder;

import az.atlacademy.orderms.exception.CustomFeignException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;

import org.springframework.web.ErrorResponse;

import static az.atlacademy.orderms.model.enums.ErrorMessage.CLIENT_ERROR;


public class CustomErrorDecoder implements ErrorDecoder {
    @SneakyThrows
    @Override
    public Exception decode(String methodKey, org.apache.coyote.Response response) {
        var objectMapper = new ObjectMapper();
        var errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);

        return new CustomFeignException(CLIENT_ERROR.getMessage(), errorResponse.getBody().getStatus());
    }
}
