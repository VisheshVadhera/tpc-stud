package com.vishesh.tpc_stud.core.repos;

import com.vishesh.tpc_stud.auth.services.AuthService;
import com.vishesh.tpc_stud.auth.services.UserService;
import com.vishesh.tpc_stud.core.models.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.mockito.Mockito.anyMapOf;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {

    private static final int MOCK_USER_ID = 99;

    private UserRepository userRepository;

    @Mock
    private AuthService authService;
    @Mock
    private UserService userService;

    @Before
    public void setup() {
        userRepository = new UserRepository(authService, userService);
    }

    @Test
    public void testEmailLogin() {
        Map<String, String> map = anyMapOf(String.class, String.class);

        userRepository.emailLogin(map);

        verify(authService).emailLogin(map);
    }

    @Test
    public void testUpdateUser() {

        User mockUser = Mockito.mock(User.class);

        userRepository.updateUser(MOCK_USER_ID, mockUser);

        verify(userService).updateUser(MOCK_USER_ID, mockUser);
    }

    @Test
    public void testGetCurrentUser() {
        userRepository.getCurrentUser();

        verify(userService).getCurrentUser();
    }

    @Test
    public void testLogout() {
        userRepository.logout();

        verify(userService).logout();
    }

    @Test
    public void testGetProfile() {
        userRepository.getProfile(MOCK_USER_ID);

        verify(userService).getProfile(MOCK_USER_ID);
    }
}