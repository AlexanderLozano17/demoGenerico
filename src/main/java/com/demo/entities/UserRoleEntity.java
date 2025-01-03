package com.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa la relación entre un usuario y un rol dentro del sistema.
 * Esta clase mapea a la tabla 'user_roles' en la base de datos.
 * Un registro en esta tabla vincula un usuario con un rol, estableciendo la relación muchos a muchos 
 * a través de la tabla intermedia.
 */
@Entity
@Table(name = "user_roles")
public class UserRoleEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Relación con la entidad 'UserEntity'.
     * Un usuario puede tener múltiples roles. Este campo mapea la relación con el usuario 
     * que posee el rol. La relación es de muchos a uno.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;
    
    /**
     * Relación con la entidad 'RolEntity'.
     * Un rol puede ser asignado a múltiples usuarios. Este campo mapea la relación con el rol 
     * asociado al usuario. La relación es de muchos a uno.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rol_id", referencedColumnName = "id", nullable = false)
    private RolEntity rolEntity;

    /**
     * Constructor vacío para la creación de la entidad.
     * Llama al constructor de la clase base 'BaseEntity'.
     */
    public UserRoleEntity() {
        super();
    }

    /**
     * Obtiene la entidad 'UserEntity' asociada a este registro.
     * @return el usuario asociado a este rol.
     */
    public UserEntity getUserEntity() {
        return userEntity;
    }

    /**
     * Establece la entidad 'UserEntity' asociada a este registro.
     * @param user la entidad 'UserEntity' a asignar.
     */
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * Obtiene la entidad 'RolEntity' asociada a este registro.
     * @return el rol asociado a este usuario.
     */
    public RolEntity getRolEntity() {
        return rolEntity;
    }

    /**
     * Establece la entidad 'RolEntity' asociada a este registro.
     * @param rolEntity la entidad 'RolEntity' a asignar.
     */
    public void setRolEntity(RolEntity rolEntity) {
        this.rolEntity = rolEntity;
    }
}