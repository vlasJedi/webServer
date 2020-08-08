package com.webApp.repos.SessionRepo;

import com.webApp.repos.UserRepo.User;

import javax.persistence.*;

@Entity
@Table(name = "SESSIONS", schema = "public")
public class Session {
    @Id
    @Column(name = "session_id")
    private String sessionId;

    @OneToOne
    @JoinColumn(name = "user_username", nullable = false)
    private User user;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
