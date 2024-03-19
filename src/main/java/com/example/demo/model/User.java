package com.example.demo.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Authority> authorities;	

    // UserDetails インターフェースで必要なメソッドを実装します。

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // アカウントの有効期限が切れていないことを示すメソッド
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // アカウントがロックされていないことを示すメソッド
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 資格情報が有効期限切れでないことを示すメソッド
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // アカウントが有効であることを示すメソッド
    @Override
    public boolean isEnabled() {
        return true;
    }

    // getter, setter, その他のロジック...
}
