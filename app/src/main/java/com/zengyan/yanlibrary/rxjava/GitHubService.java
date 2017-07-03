package com.zengyan.yanlibrary.rxjava;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ZengYan on 2017/6/6.
 * Email : 810989881@qq.com
 *
 * @decs :
 */

public interface GitHubService {

    @GET("users/{user}/repos")
    Call<List<String>> listRepos(@Path("user") String user);


}
