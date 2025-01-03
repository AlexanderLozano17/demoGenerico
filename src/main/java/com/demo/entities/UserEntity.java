package com.demo.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entidad que representa un usuario dentro del sistema.
 * Esta clase mapea a la tabla 'users' en la base de datos.
 * Un usuario puede tener múltiples roles asignados a través de la tabla intermedia 'user_roles'.
 */
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Nombre completo del usuario.
     * Este campo es obligatorio y debe ser único en la base de datos.
     * Longitud máxima: 60 caracteres.
     */
    @Column(length = 60, nullable = false, unique = true)
    private String name;

    /**
     * Nombre de usuario único para la autenticación.
     * Este campo es obligatorio y debe ser único en la base de datos.
     * Longitud máxima: 40 caracteres.
     */
    @Column(length = 40, nullable = false, unique = true)
    private String username;

    /**
     * Correo electrónico del usuario.
     * Este campo es obligatorio y debe ser único en la base de datos.
     * Longitud máxima: 80 caracteres.
     */
    @Column(length = 80, nullable = false, unique = true)
    private String email;

    /**
     * Contraseña del usuario.
     * Este campo es obligatorio y se almacena de manera segura (encriptada).
     * Longitud máxima: 80 caracteres.
     */
    @Column(length = 80, nullable = false)
    private String password;

    /**
     * Indica si el usuario está activo o no.
     * Este campo es obligatorio. Los usuarios inactivos no podrán acceder al sistema.
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    /**
     * Indica si la cuenta del usuario está bloqueada.
     * Este campo es obligatorio. Un usuario bloqueado no podrá acceder al sistema.
     */
    @Column(name = "is_locked", nullable = false)
    private Boolean isLocked;

    /**
     * Token para restablecer la contraseña del usuario.
     * Este campo es opcional y se utiliza solo cuando el usuario solicita un restablecimiento de contraseña.
     * Longitud máxima: 80 caracteres.
     */
    @Column(name = "reset_token", length = 80)
    private String resetToken;

    /**
     * Fecha y hora de expiración del token de restablecimiento.
     * Este campo es opcional y se usa solo cuando el usuario solicita un restablecimiento de contraseña.
     */
    @Column(name = "reset_token_expiry")
    private LocalDateTime resetTokenExpiry;

    /**
     * Indica si el correo electrónico del usuario ha sido verificado.
     * Este campo es obligatorio. Un usuario con correo no verificado puede ser restringido en ciertas acciones.
     */
    @Column(name = "is_email_verified", nullable = false)
    private Boolean isEmailVerified;

    /**
     * Indica si el teléfono del usuario ha sido verificado.
     * Este campo es opcional, dependiendo de la implementación del sistema.
     */
    @Column(name = "is_phone_verified")
    private Boolean isPhoneVerified;

    /**
     * Fecha y hora de la última actualización de la contraseña.
     * Este campo se actualiza cada vez que el usuario cambia su contraseña.
     */
    @Column(name = "password_updated_at")
    private LocalDateTime passwordUpdatedAt;

    /**
     * Relación con UserRoleEntity (tabla intermedia).
     * Un usuario puede tener múltiples roles asignados. La relación se gestiona en la clase `UserRoleEntity`.
     * El atributo `mappedBy` hace referencia a la propiedad en `UserRoleEntity` que indica la relación inversa.
     * El cambio en esta relación propagará cambios a la tabla intermedia.
     */
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRoleEntity> userRoles = new HashSet<>();

    // Getters y Setters

    /**
     * Obtiene el nombre del usuario.
     * @return el nombre del usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del usuario.
     * @param name el nombre del usuario a establecer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el nombre de usuario.
     * @return el nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     * @param username el nombre de usuario a establecer.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * @return el correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * @param email el correo electrónico a establecer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return la contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password la contraseña a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el estado de actividad del usuario.
     * @return el estado de actividad del usuario.
     */
    public Boolean getIsActive() {
        return isActive; 
    }

    /**
     * Establece el estado de actividad del usuario.
     * @param isActive el estado de actividad a establecer.
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Obtiene el estado de bloqueo de la cuenta del usuario.
     * @return el estado de bloqueo de la cuenta.
     */
    public Boolean getIsLocked() {
        return isLocked;
    }

    /**
     * Establece el estado de bloqueo de la cuenta del usuario.
     * @param isLocked el estado de bloqueo a establecer.
     */
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * Obtiene el token de restablecimiento de la contraseña del usuario.
     * @return el token de restablecimiento.
     */
    public String getResetToken() {
        return resetToken;
    }

    /**
     * Establece el token de restablecimiento de la contraseña.
     * @param resetToken el token de restablecimiento a establecer.
     */
    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    /**
     * Obtiene la fecha de expiración del token de restablecimiento.
     * @return la fecha de expiración del token.
     */
    public LocalDateTime getResetTokenExpiry() {
        return resetTokenExpiry;
    }

    /**
     * Establece la fecha de expiración del token de restablecimiento.
     * @param resetTokenExpiry la fecha de expiración del token a establecer.
     */
    public void setResetTokenExpiry(LocalDateTime resetTokenExpiry) {
        this.resetTokenExpiry = resetTokenExpiry;
    }

    /**
     * Obtiene el estado de verificación del correo electrónico del usuario.
     * @return el estado de verificación del correo electrónico.
     */
    public Boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    /**
     * Establece el estado de verificación del correo electrónico.
     * @param isEmailVerified el estado de verificación del correo electrónico a establecer.
     */
    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    /**
     * Obtiene el estado de verificación del teléfono del usuario.
     * @return el estado de verificación del teléfono.
     */
    public Boolean getIsPhoneVerified() {
        return isPhoneVerified;
    }

    /**
     * Establece el estado de verificación del teléfono.
     * @param isPhoneVerified el estado de verificación del teléfono a establecer.
     */
    public void setIsPhoneVerified(Boolean isPhoneVerified) {
        this.isPhoneVerified = isPhoneVerified;
    }

    /**
     * Obtiene la fecha de la última actualización de la contraseña.
     * @return la fecha de última actualización de la contraseña.
     */
    public LocalDateTime getPasswordUpdatedAt() {
        return passwordUpdatedAt;
    }

    /**
     * Establece la fecha de última actualización de la contraseña.
     * @param passwordUpdatedAt la fecha de última actualización a establecer.
     */
    public void setPasswordUpdatedAt(LocalDateTime passwordUpdatedAt) {
        this.passwordUpdatedAt = passwordUpdatedAt;
    }

    /**
     * Obtiene los roles asignados al usuario a través de la tabla intermedia.
     * @return los roles del usuario.
     */
    public Set<UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    /**
     * Establece los roles asignados al usuario.
     * @param userRoles los roles a establecer.
     */
    public void setUserRoles(Set<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }
}
