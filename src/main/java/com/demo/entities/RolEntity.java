package com.demo.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entidad que representa un rol dentro del sistema.
 * Esta entidad está asociada con la tabla 'roles' en la base de datos.
 * Cada rol puede estar vinculado a varios usuarios a través de la tabla intermedia 'user_roles'.
 */
@Entity
@Table(name = "roles")
public class RolEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * Nombre del rol.
     * Este campo debe ser único y no puede ser nulo.
     */
    @Column(length = 60, nullable = false, unique = true)
    private String name;
    
    /**
     * Descripción del rol.
     * Este campo proporciona detalles adicionales sobre el rol y no puede ser nulo.
     */
    @Column(length = 100, nullable = false)
    private String description;
    
    /**
     * Relación con la entidad UserRoleEntity, que funciona como tabla intermedia entre 'roles' y 'users'.
     * Un rol puede estar asociado a múltiples registros de usuarios a través de la tabla intermedia.
     * Esta relación es de uno a muchos.
     */
    @OneToMany(mappedBy = "rolEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRoleEntity> userRolesEntity = new HashSet<>();

    /**
     * Constructor por defecto
     */
    public RolEntity() {
        super();
    }

    /**
     * Obtiene el nombre del rol.
     * @return el nombre del rol.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del rol.
     * @param name el nombre del rol a establecer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la descripción del rol.
     * @return la descripción del rol.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del rol.
     * @param description la descripción del rol a establecer.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene los registros de UserRoleEntity asociados a este rol.
     * @return el conjunto de registros de la tabla intermedia asociados a este rol.
     */
    public Set<UserRoleEntity> getUserRoles() {
        return userRolesEntity;
    }

    /**
     * Establece los registros de UserRoleEntity asociados a este rol.
     * @param userRolesEntity el conjunto de registros de la tabla intermedia a establecer.
     */
    public void setUserRolesEntity(Set<UserRoleEntity> userRolesEntity) {
        this.userRolesEntity = userRolesEntity;
    }
}