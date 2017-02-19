package com.vishesh.tpc_stud.auth.services;

import com.vishesh.tpc_stud.auth.models.AccessToken;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by vishesh on 14/2/17.
 */
public interface AuthService {

    @POST("users/emailLogin")
    Single<AccessToken> emailLogin(@Body Map<String, String> map);

}
