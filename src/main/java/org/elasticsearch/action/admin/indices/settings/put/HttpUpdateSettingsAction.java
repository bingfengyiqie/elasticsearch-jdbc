
package org.elasticsearch.action.admin.indices.settings.put;

import com.google.common.base.Joiner;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.common.bytes.ChannelBufferBytesReference;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.xbib.elasticsearch.helper.client.http.HttpAction;
import org.xbib.elasticsearch.helper.client.http.HttpInvocationContext;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class HttpUpdateSettingsAction extends HttpAction<UpdateSettingsRequest, UpdateSettingsResponse> {

    public HttpUpdateSettingsAction(Settings settings) {
        super(settings, UpdateSettingsAction.NAME);
    }

    @Override
    protected HttpRequest createHttpRequest(URL url, UpdateSettingsRequest request) throws IOException {
        XContentBuilder builder = jsonBuilder();
        builder.startObject();
        request.settings().toXContent(builder, ToXContent.EMPTY_PARAMS);
        builder.endObject();
        String index = request.indices() != null ? "/" + Joiner.on(",").join(request.indices()) : "" ;
        return newRequest(HttpMethod.PUT, url, index + "/_settings", builder.string());
    }

    @Override
    protected UpdateSettingsResponse createResponse(HttpInvocationContext<UpdateSettingsRequest,UpdateSettingsResponse> httpInvocationContext) throws IOException {
        if (httpInvocationContext == null) {
            throw new IllegalStateException("no http context");
        }
        HttpResponse httpResponse = httpInvocationContext.getHttpResponse();
        BytesReference ref = new ChannelBufferBytesReference(httpResponse.getContent());
        Map<String,Object> map = JsonXContent.jsonXContent.createParser(ref).map();
        return new UpdateSettingsResponse();
    }
}
