package home.hallgassaszivedre.infrastructure.acl;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.stereotype.Component;

@Component
public class ObjectSerializer {

    private ObjectMapper mapper;

    public ObjectSerializer() {
        mapper = null;
    }

    public byte[] getJsonBytes(Serializable obj) {
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (JsonGenerationException e) {
            rethrow(e);
        } catch (JsonMappingException e) {
            rethrow(e);
        } catch (IOException e) {
            rethrow(e);
        }

        return new byte[]{};
    }

    public String getJson(Serializable obj) {
        String result = null;
        try {
            result = mapper.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            rethrow(e);
        } catch (JsonMappingException e) {
            rethrow(e);
        } catch (IOException e) {
            rethrow(e);
        }
        return result;
    }


    public <T extends Serializable> T getObject(String str, Class<T> clazz) {
        T result = null;
        try {
            result = mapper.readValue(str, clazz);
        } catch (JsonParseException e) {
            rethrow(e);
        } catch (JsonMappingException e) {
            rethrow(e);
        } catch (IOException e) {
            rethrow(e);
        }
        return result;
    }

    public <T extends Serializable> T getObject(byte[] bytes, Class<T> clazz) {
        T result = null;
        try {
            result = mapper.readValue(bytes, 0, bytes.length, clazz);
        } catch (JsonParseException e) {
            rethrow(e);
        } catch (JsonMappingException e) {
            rethrow(e);
        } catch (IOException e) {
            rethrow(e);
        }
        return result;
    }

    private void rethrow(Exception e) {
        throw new RuntimeException(e);
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    @Resource
    public void setMapper(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
        this.mapper.enable(Feature.USE_BIG_INTEGER_FOR_INTS);
        this.mapper.setSerializationInclusion(Inclusion.NON_NULL);
        this.mapper.disable(Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.mapper.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, "type");
    }

}