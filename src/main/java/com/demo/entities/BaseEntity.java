package com.demo.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

/**
 * Clase base para todas las entidades. Esta clase contiene los campos comunes
 * para gestionar fechas de creación, actualización y eliminación (soft delete).
 * Las entidades que extienden esta clase heredarán estos campos y su lógica.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID único de la entidad. Generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha de creación de la entidad.
     * Este campo no se puede actualizar después de la creación.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Fecha de la última actualización de la entidad.
     * Se actualiza automáticamente cada vez que se modifica la entidad.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Fecha de eliminación de la entidad, utilizada para el "soft delete".
     * Solo se establece cuando la entidad se marca como eliminada lógicamente.
     */
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    /**
     * Indica si la entidad ha sido eliminada lógicamente (soft delete).
     * Se establece a true cuando la entidad es eliminada lógicamente.
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    // Getters y Setters para cada campo

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    // Métodos de actualización automática de fechas

    /**
     * Método que se ejecuta automáticamente antes de insertar la entidad.
     * Se utiliza para establecer la fecha de creación.
     */
    @PrePersist 
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Método que se ejecuta automáticamente antes de actualizar la entidad.
     * Se utiliza para establecer la fecha de la última actualización.
     */
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    } 

    /** 
     * Método que se ejecuta antes de eliminar la entidad.
     * Realiza un "soft delete" estableciendo el campo `isDeleted` a `true`
     * y registrando la fecha de eliminación.
     */
    @PreRemove
    public void onRemove() {
        // Marca la entidad como eliminada lógicamente
        this.isDeleted = true;

        // Establece la fecha y hora de eliminación
        this.deletedAt = LocalDateTime.now();
    }
}
