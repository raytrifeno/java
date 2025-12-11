/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mypos.auth.model;

/**
 *
 * @author raysu
 */
public class UserSession {
    // 1. Instance Static (Hanya ada satu di seluruh aplikasi)
    private static UserSession instance;
    
    // 2. Data User yang sedang login
    private User currentUser;
    
    // 3. Constructor Private (Supaya tidak bisa di-new sembarangan)
    private UserSession() {}
    
    // 4. Method untuk mengambil Instance (Pintu Masuk)
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }
    
    // 5. Getter & Setter untuk User
    public User getUser() {
        return currentUser;
    }
    
    public void setUser(User user) {
        this.currentUser = user;
    }
    
    // 6. Method Logout (Hapus sesi)
    public void clearSession() {
        this.currentUser = null;
    }
    
    // Cek apakah ada user login
    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
