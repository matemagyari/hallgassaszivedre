package home.hallgassaszivedre.infrastructure.acl;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Named;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@Named
public class ObjectSerializer {

    private ObjectMapper mapper;

    public ObjectSerializer() {
        mapper = new ObjectMapper();
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

}
