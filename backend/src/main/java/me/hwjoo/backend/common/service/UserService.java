// src/main/java/me/hwjoo/backend/common/service/UserService.java
package me.hwjoo.backend.common.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService {
    private final UUID dummyUserUuid = UUID.randomUUID();

    public UUID getDummyUserUuid() {
        return dummyUserUuid;
    }
}
