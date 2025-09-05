package com.jhp.foryouth.join.service;

import com.jhp.foryouth.user.entity.User;
import com.jhp.foryouth.user.entity.UserAuth;
import com.jhp.foryouth.user.dto.UserDTO;

public interface JoinService {

    void join(UserDTO userDTO);


    default User dtoToUserEntity(UserDTO dto) {
        User userEntity = User.builder().num(dto.getNum())
                .userPhone(dto.getUserPhone())
                .userName(dto.getUserName())
                .userBirth(dto.getUserBirth())
                .userEmail(dto.getUserEmail())
                .provider("normal")
                .agreedEventAlarm(dto.getAgreedEventAlarm()).build();

        return userEntity;
    }

    default UserDTO userEntityToDTO(User entity) {
        UserDTO userDTO = UserDTO.builder().num(entity.getNum())
                .userPhone(entity.getUserPhone())
                .userName(entity.getUserName())
                .userBirth(entity.getUserBirth())
                .userEmail(entity.getUserEmail())
                .agreedEventAlarm(entity.getAgreedEventAlarm()).build();

        return userDTO;
    }

    default UserAuth dtoToUserAuthEntity(User user, UserDTO dto) {
        UserAuth authEntity = UserAuth.builder().num(dto.getNum())
                .userId(dto.getUserId())
                .userPw(dto.getUserPw())
                .user(user).build();

        return authEntity;
    }

    default UserDTO AuthuserEntityToDTO(UserAuth entity) {
        UserDTO userDTO = UserDTO.builder().num(entity.getNum())
                .userId(entity.getUserId())
                .userPw(entity.getUserPw())
                .userNum(entity.getUser().getNum()).build();

        return userDTO;
    }
}
