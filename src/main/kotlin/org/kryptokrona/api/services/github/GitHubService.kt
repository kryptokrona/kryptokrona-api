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

package org.kryptokrona.api.services.github

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.kryptokrona.api.models.response.GitHubContributorResponse
import org.kryptokrona.api.models.response.GitHubRepositoryResponse
import org.slf4j.LoggerFactory

class GitHubService {

    private val logger = LoggerFactory.getLogger("GitHubService")

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    private val githubRepositoriesUrl =
        "https://api.github.com/orgs/kryptokrona/repos?type=public&sort=updated&direction=desc"

    private val githubBaseApiUrl = "https://api.github.com/repos"

    private val githubRepos = mutableListOf<String>()

    private var githubGitHubContributorResponses = mutableListOf<GitHubContributorResponse>()

    private suspend fun getRepositories() = coroutineScope {
        try {
            val token = System.getenv("GITHUB_TOKEN")
            val response: HttpResponse = client.get(githubRepositoriesUrl) {
                header("Accept", "application/vnd.github+json")
                header("Authorization", "Bearer $token")
                header("X-GitHub-Api-Version", "2022-11-28")
            }

            val jsonString: String = response.bodyAsText()

            val json = Json { ignoreUnknownKeys = true }
            val repositories: List<GitHubRepositoryResponse> = json.decodeFromString(jsonString)

            for (repo in repositories) {
                githubRepos.add(repo.fullName)
            }
        } catch (e: Exception) {
            println("Error getting repositories from GitHub: ${e.message}")
        }
    }

    suspend fun getContributors(): List<GitHubContributorResponse> = coroutineScope {
        launch {
            getRepositories()

            githubRepos.forEach { repo ->
                try {
                    val token = System.getenv("GITHUB_TOKEN")

                    val response: HttpResponse = client.get("$githubBaseApiUrl/$repo/contributors") {
                        header("Accept", "application/vnd.github+json")
                        header("Authorization", "Bearer $token")
                        header("X-GitHub-Api-Version", "2022-11-28")
                    }

                    val jsonString: String = response.bodyAsText()

                    val json = Json { ignoreUnknownKeys = true }

                    logger.info(jsonString)

                    json.decodeFromString<List<GitHubContributorResponse>>(jsonString)
                } catch (e: Exception) {
                    println("Error getting contributors from GitHub: ${e.message}")
                }
            }
        }

        githubGitHubContributorResponses
    }
}