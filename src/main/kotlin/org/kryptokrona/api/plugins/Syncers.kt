package org.kryptokrona.api.plugins

import io.ktor.server.application.*
import kotlinx.coroutines.async
import org.kryptokrona.api.syncers.HuginSyncer

fun Application.configureSyncers() {
    val defferedPostEncrypted = async { HuginSyncer().postEncryptedSync() }
    val defferedPostEncryptedGroup = async { HuginSyncer().postEncryptedGroupSync() }
}