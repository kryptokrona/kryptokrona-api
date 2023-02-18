package org.kryptokrona.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

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

    private List<Transaction> transactions;

}
