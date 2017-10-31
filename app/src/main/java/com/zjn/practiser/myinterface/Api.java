package com.zjn.practiser.myinterface;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Marks zhang on 2017/10/20.
 */

public interface Api {
    @GET
    Observable<Response<ResponseBody>> getTop250(@Query("start") int start, @Query("count") int count);
}
