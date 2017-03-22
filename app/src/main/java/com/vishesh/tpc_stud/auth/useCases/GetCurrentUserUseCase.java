package com.vishesh.tpc_stud.auth.useCases;

import com.vishesh.tpc_stud.core.helpers.BaseUseCase;
import com.vishesh.tpc_stud.core.models.User;
import com.vishesh.tpc_stud.core.repos.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;


public class GetCurrentUserUseCase extends BaseUseCase<User, Object, Object>{

    private final UserRepository userRepository;

    @Inject
    public GetCurrentUserUseCase(@Named("jobScheduler") Scheduler jobScheduler,
                                 @Named("postJobScheduler") Scheduler postJobScheduler,
                                 UserRepository userRepository) {
        super(jobScheduler, postJobScheduler);
        this.userRepository = userRepository;
    }


    @Override
    public Single<User> buildSingle(Object o, Object o2) {
        return userRepository.getCurrentUser();
    }
}
