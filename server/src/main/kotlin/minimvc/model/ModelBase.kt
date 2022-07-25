package minimvc.model

abstract class ModelBase {

    companion object NullModel : ModelBase() {
        override fun isValid() : Boolean {
            return true
        }
    }

    val constraints: List<Function<Boolean>> = emptyList()

    fun notNull(obj: Any) : Boolean {
        return obj != null
    }

    fun validate() {
        constraints.forEach(){
            f -> f.apply {  }
        }
    }

    abstract fun isValid() : Boolean
}