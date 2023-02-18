package org.kryptokrona.api.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Transaction entity.
 *
 * @author Marcus Cvjeticanin
 */
public class Transaction {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
