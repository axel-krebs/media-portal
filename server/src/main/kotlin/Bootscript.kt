import com.afrtn.portal.QApp
import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.annotations.QuarkusMain

/**
 * Read configuration and start framework.
 */
open class Bootstrap {

    @QuarkusMain
    companion object {
        @JvmStatic
        fun main(vararg args: String) {
            Quarkus.run(QApp::class.java, *args)
        }
    }
}
