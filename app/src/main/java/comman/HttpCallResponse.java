package comman;

/**
 * Created by Surya on 10/07/18.
 */
public interface HttpCallResponse {

    void OnSuccess(Object obj);

    void OnFailure(Throwable t);

}
