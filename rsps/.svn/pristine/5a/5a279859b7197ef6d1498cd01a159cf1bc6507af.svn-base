package com.izhuixin.rsps.common;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

public class JsonHttpMessageConverter extends AbstractGenericHttpMessageConverter<Object> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private Gson readGson = new Gson();
    private Gson writeGson = new Gson();
    private String jsonPrefix;

    public JsonHttpMessageConverter() {
        super(new MediaType[]{MediaType.APPLICATION_JSON, new MediaType("application", "*+json")});
        this.setDefaultCharset(DEFAULT_CHARSET);
    }

    public void setWriteGson(Gson gson) {
        Assert.notNull(gson, "'gson' is required");
        this.writeGson = gson;
    }

    public void setReadGson(Gson gson) {
        Assert.notNull(gson, "'gson' is required");
        this.writeGson = gson;
    }

    public Gson getGson() {
        return this.writeGson;
    }

    public void setJsonPrefix(String jsonPrefix) {
        this.jsonPrefix = jsonPrefix;
    }

    public void setPrefixJson(boolean prefixJson) {
        this.jsonPrefix = prefixJson ? ")]}', " : null;
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        TypeToken<?> token = this.getTypeToken(type);
        return this.readTypeToken(token, inputMessage);
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        TypeToken<?> token = this.getTypeToken(clazz);
        return this.readTypeToken(token, inputMessage);
    }

    /** @deprecated */
    @Deprecated
    protected TypeToken<?> getTypeToken(Type type) {
        return TypeToken.get(type);
    }

    private Object readTypeToken(TypeToken<?> token, HttpInputMessage inputMessage) throws IOException {
        InputStreamReader json = new InputStreamReader(inputMessage.getBody(), this.getCharset(inputMessage.getHeaders()));

        try {
            return this.readGson.fromJson(json, token.getType());
        } catch (JsonParseException var5) {
            System.err.println("JsonHttpMessageConverter parse error");
            throw new HttpMessageNotReadableException("JSON parse error: " + var5.getMessage(), var5);
        }
    }

    private Charset getCharset(HttpHeaders headers) {
        return headers != null && headers.getContentType() != null && headers.getContentType().getCharset() != null ? headers.getContentType().getCharset() : DEFAULT_CHARSET;
    }

    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Charset charset = this.getCharset(outputMessage.getHeaders());
        OutputStreamWriter writer = new OutputStreamWriter(outputMessage.getBody(), charset);

        try {
            if (this.jsonPrefix != null) {
                writer.append(this.jsonPrefix);
            }

            if (type != null) {
                this.writeGson.toJson(o, type, writer);
            } else {
                this.writeGson.toJson(o, writer);
            }

            writer.close();
        } catch (JsonIOException var7) {
            throw new HttpMessageNotWritableException("Could not write JSON: " + var7.getMessage(), var7);
        }
    }
}
