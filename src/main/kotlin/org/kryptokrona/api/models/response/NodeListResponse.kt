package org.kryptokrona.api.models.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Node(
    val name: String,
    val url: String,
    val port: Int,
    val ssl: Boolean,
    val cache: Boolean,
    val version: String,
    val fee: String,
    @SerialName("proxy_url") val proxyUrl: String
)

@Serializable
data class NodeListResponse(
    val nodes: List<Node>
)
