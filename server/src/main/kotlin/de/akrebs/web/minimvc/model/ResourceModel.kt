package de.akrebs.web.minimvc.model

class ResourceModel(private val resource: ByteArray?) : ModelBase() {

    fun getAsByteArray() : ByteArray? {
        return resource
    }
}