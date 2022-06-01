package http;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ConnectionInterceptor implements Interceptor
{
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request newRequest  = chain.request().newBuilder().addHeader("User-Agent", "request").build();

        return chain.proceed(newRequest);
    }


}
