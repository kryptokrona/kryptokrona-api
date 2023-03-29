package org.kryptokrona.api.models.response

import kotlinx.serialization.Serializable

@Serializable
data class ExceptionResponse(val message: String)
