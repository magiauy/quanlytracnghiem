package com.sgu.quanlytracnghiem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private boolean isAdmin;
}
