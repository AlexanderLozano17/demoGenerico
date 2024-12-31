package com.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;
        
    public BaseEntity() {
		super();
	}
    
    /**
     * 
     * @param id
     * @param createdDate
     * @param updatedDate
     */
	public BaseEntity(Long id, Date createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Métodos para actualizar automáticamente las fechas
	 */
    @PrePersist
    protected void onCreate() {
        if (createdDate == null) {
            createdDate = new Date();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }
}
