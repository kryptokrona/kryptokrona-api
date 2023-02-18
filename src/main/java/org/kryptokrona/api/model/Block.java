package org.kryptokrona.api.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Block entity.
 *
 * @author Marcus Cvjeticanin
 */
public class Block {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private Long time;

    private float difficulty;

    private float reward;

    @JsonProperty("total_fees")
	@Column(name = "total_fees")
    private Long totalFees;

    @JsonProperty("block_size")
	@Column(name = "block_size")
    private Long blockSize;

    @JsonProperty("previous_hash")
	@Column(name = "previous_hash")
    private String previousHash;

    @JsonProperty("created_at")
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

    @ManyToMany(mappedBy="blocks")
    private List<Transaction> transactions;

    @JsonGetter("transactions")
	public List<String> getAllPosts() {
		if (transactions != null) {
			return transactions.stream()
					.map(t -> {
						return "/api/v1/transactions/" + t.getId();
					}).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public float getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(float difficulty) {
        this.difficulty = difficulty;
    }

    public float getReward() {
        return reward;
    }

    public void setReward(float reward) {
        this.reward = reward;
    }

    public Long getTotalFees() {
        return totalFees;
    }

    public void setTotalFees(Long totalFees) {
        this.totalFees = totalFees;
    }

    public Long getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(Long blockSize) {
        this.blockSize = blockSize;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
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
