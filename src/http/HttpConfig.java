package http;

import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpConfig
{
    private static HttpConfig instance;
    private Retrofit retrofit;
    private OkHttpClient client;

    private HttpConfig()
    {
        createOkHttp();
    }

    private void createOkHttp()
    {
        client = new OkHttpClient.Builder()
                .addInterceptor( new ConnectionInterceptor() )
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.github.com/")
                .addConverterFactory( GsonConverterFactory.create() )
                .addCallAdapterFactory( RxJava3CallAdapterFactory.createWithScheduler( Schedulers.trampoline() ) )
                .build();
    }

    public static synchronized HttpConfig getInstance()
    {
        if( instance == null )
            instance = new HttpConfig();

        return instance;
    }

    public GetMethods apiGet()
    {
        return retrofit.create(GetMethods.class);
    }

    public void stopAllRequests()
    {
        if( client != null )
            client.dispatcher().cancelAll();
    }
}
