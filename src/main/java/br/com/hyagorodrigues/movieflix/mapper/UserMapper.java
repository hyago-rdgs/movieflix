package br.com.hyagorodrigues.movieflix.mapper;

import br.com.hyagorodrigues.movieflix.controller.request.UserRequest;
import br.com.hyagorodrigues.movieflix.controller.response.UserResponse;
import br.com.hyagorodrigues.movieflix.entity.User;

public class UserMapper {
    public static User toUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();
    }

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
