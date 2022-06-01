import http.HttpConfig;
import http.pojo.Repo;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Main
{
    private static HttpConfig httpConfig = null;

    public static void main(String[] args)
    {
        httpConfig = HttpConfig.getInstance();
        setData();
    }

    private static void setData()
    {
        Disposable disposable = httpConfig.apiGet().listRepos("Reflector93")
                .observeOn( Schedulers.single() )
                .subscribe
                        (
                                repo ->
                                {
                                    for( Repo itemRepo: repo )
                                        System.out.println( itemRepo.getName() );
                                },
                                throwable -> System.out.println("Error")
                        );
    }
}
