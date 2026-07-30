package com.codeForLearn.live_chat_application.service.online;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OnlineUserService {

    /*
     * Stores usernames of currently online users.
     */
    private final Set<String> onlineUsers = new HashSet<>();


    /*
     * Add user when they join.
     */
    public void addUser(String username) {

        onlineUsers.add(username);

        System.out.println("Online User Added : " + username);
        printOnlineUsers();
    }


    /*
     * Remove user when they leave.
     */
    public void removeUser(String username) {

        onlineUsers.remove(username);

        System.out.println("Online User Removed : " + username);
        printOnlineUsers();
    }


    /*
     * Get all online users.
     */
    public Set<String> getOnlineUsers() {

        return new HashSet<>(onlineUsers);
    }


    /*
     * Get online user count.
     */
    public int getOnlineUserCount() {

        return onlineUsers.size();
    }


    /*
     * Print online users for debugging.
     */
    private void printOnlineUsers() {

        System.out.println("--------------------------------");
        System.out.println(
                "Online Users : " + onlineUsers
        );
        System.out.println(
                "Online Count : " + onlineUsers.size()
        );
        System.out.println("--------------------------------");
    }
}