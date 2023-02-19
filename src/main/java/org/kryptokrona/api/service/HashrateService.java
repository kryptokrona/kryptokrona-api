// Copyright (c) 2023-2023, The Kryptokrona Developers
//
// Written by Marcus Cvjeticanin
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, are
// permitted provided that the following conditions are met:
//
// 1. Redistributions of source code must retain the above copyright notice, this list of
//    conditions and the following disclaimer.
//
// 2. Redistributions in binary form must reproduce the above copyright notice, this list
//    of conditions and the following disclaimer in the documentation and/or other
//    materials provided with the distribution.
//
// 3. Neither the name of the copyright holder nor the names of its contributors may be
//    used to endorse or promote products derived from this software without specific
//    prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
// EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
// THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
// PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
// STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
// THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package org.kryptokrona.api.service;

import org.kryptokrona.api.model.Hashrate;
import org.kryptokrona.api.repository.HashrateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Hashrate Service.
 *
 * @author Marcus Cvjeticanin
 */
@Service
public class HashrateService {

    private static final Logger log = LoggerFactory.getLogger(HashrateService.class);

    private final HashrateRepository hashrateRepository;

    @Autowired
    public HashrateService(HashrateRepository hashrateRepository) {
        this.hashrateRepository = hashrateRepository;
    }

    public Page<Hashrate> getAll(int page, int size, String order) {
		if (Objects.equals(order, "asc".toLowerCase())) {
			var paging = PageRequest.of(page, size, Sort.by("id").ascending());
			return hashrateRepository.findAll(paging);
		}

		var paging = PageRequest.of(page, size, Sort.by("id").descending());
		return hashrateRepository.findAll(paging);
	}

    public Hashrate getById(long id) {
		if (hashrateRepository.existsById(id)) {
			Hashrate hashrate = hashrateRepository.findById(id).get();
			log.info("Hashrate found with ID: " + id);
			return hashrate;
		}

		log.info("Unable to find hashrate with ID: " + id);

		return null;
	}

}
