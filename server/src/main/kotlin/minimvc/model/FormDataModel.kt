package minimvc.model

abstract class FormDataModel : ModelBase() {

    abstract fun apply(map: Map<String, Any>)

    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }
}