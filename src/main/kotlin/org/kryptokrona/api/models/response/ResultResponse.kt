package org.kryptokrona.api.models.response

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(@Contextual val items: Any, val page: Int, val size: Int, val total: Int)
