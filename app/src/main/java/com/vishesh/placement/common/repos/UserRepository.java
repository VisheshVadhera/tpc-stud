package com.vishesh.placement.common.repos;

import com.vishesh.placement.auth.models.AccessToken;
import com.vishesh.placement.auth.services.AuthService;
import com.vishesh.placement.common.services.UserService;
import com.vishesh.placement.common.models.User;
import com.vishesh.placement.networkProfiles.models.NetworkProfile;
import com.vishesh.placement.dashboard.models.UserProfile;
import com.vishesh.placement.semesterGrades.models.SemesterGrade;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserRepository {

    private final AuthService authService;
    private final UserService userService;

    @Inject
    public UserRepository(AuthService authService,
                          UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    public Single<AccessToken> emailLogin(Map<String, String> params) {
        return authService.emailLogin(params);
    }

    public Single<User> updateUser(final int userId, User user) {
        return userService.updateUser(userId, user);
    }

    public Single<User> getCurrentUser() {
        return userService.getCurrentUser();
    }

    public Single<Object> logout() {
        return userService.logout();
    }

    public Single<UserProfile> getProfile(int userId) {
        return userService.getProfile(userId);
    }

    public Single<List<NetworkProfile>> getNetworkProfiles(int userId) {
        return userService.getNetworkProfiles(userId);
    }

    public Single<NetworkProfile> saveNetworkProfile(int userId, NetworkProfile networkProfile) {
        return userService.saveNetworkProfile(userId, networkProfile);
    }

    public Single<List<SemesterGrade>> getSemesterGrades(int userId) {
        return userService.getSemesterGrades(userId);
    }
}
