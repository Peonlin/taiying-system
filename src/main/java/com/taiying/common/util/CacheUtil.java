package com.taiying.common.util;

import com.taiying.user.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheUtil {

    private static Map<String, UserDTO> USER_MAP = new ConcurrentHashMap<>();

    private static Map<String, Long> USER_TIME_MAP = new ConcurrentHashMap<>();

    public static void addUserToCache(UserDTO userDTO) {
        if (USER_TIME_MAP.containsKey(userDTO.getUid())) {
            USER_TIME_MAP.put(userDTO.getUid(), System.currentTimeMillis());
        } else {
            USER_MAP.put(userDTO.getUid(), userDTO);
            USER_TIME_MAP.put(userDTO.getUid(), System.currentTimeMillis());
        }
    }

    public static UserDTO getUser(String uid) {
        UserDTO u =  USER_MAP.getOrDefault(uid, null);
        if (u != null) {
            USER_TIME_MAP.put(uid, System.currentTimeMillis());
        }
        return u;
    }

    public static void removeUserFromCache(String uid) {
        USER_MAP.remove(uid);
        USER_TIME_MAP.remove(uid);
    }

    public static void removeOutdateUser() {
        List<String> removeString = new ArrayList<>();
        USER_TIME_MAP.forEach((str, time) -> {
            if (System.currentTimeMillis() - time > 60 * 60 * 1000L) {
                USER_MAP.remove(str);
                removeString.add(str);
            }
        });
        removeString.forEach(s -> USER_TIME_MAP.remove(s));
    }

    public static boolean isLogined(String uid) {
        return USER_TIME_MAP.containsKey(uid);
    }
}
