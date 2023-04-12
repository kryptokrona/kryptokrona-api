package org.kryptokrona.api.utils

import io.micrometer.prometheus.PrometheusConfig
import io.micrometer.prometheus.PrometheusMeterRegistry

object Metrics {
    private val registry = PrometheusMeterRegistry(PrometheusConfig.DEFAULT)

    fun getRegistry(): PrometheusMeterRegistry {
        return registry
    }
}