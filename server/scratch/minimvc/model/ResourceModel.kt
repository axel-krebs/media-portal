package minimvc.model

class ResourceModel(private val resource: ByteArray?) : ModelBase() {

    fun getAsByteArray() : ByteArray? {
        return resource
    }

    override fun isValid(): Boolean {
        if (resource != null && resource.isNotEmpty()) return true
        return false
    }
}