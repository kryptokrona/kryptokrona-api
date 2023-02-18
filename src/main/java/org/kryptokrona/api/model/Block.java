package org.kryptokrona.api.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Block entity.
 *
 * @author Marcus Cvjeticanin
 */
public class Block {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

}
