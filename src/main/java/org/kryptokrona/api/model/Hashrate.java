package org.kryptokrona.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Hashrate entity.
 *
 * @author Marcus Cvjeticanin
 */
@Entity
public class Hashrate {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String hashrate;

	@JsonProperty("created_at")
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashrate() {
        return hashrate;
    }

    public void setHashrate(String hashrate) {
        this.hashrate = hashrate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

}
