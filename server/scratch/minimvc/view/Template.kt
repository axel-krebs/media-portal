package minimvc.view

import minimvc.model.ModelBase
import io.vertx.core.http.HttpServerResponse

/**
 * Intention: Reactive template engine. Out-phased by Quarkus Qute.
 */
open class Template(format: Format, data: Sequence<Char>) {

    private val d_iter = data.iterator()

    fun apply(model: ModelBase, response: HttpServerResponse?) {
        val sb : StringBuilder = StringBuilder()
        while(d_iter.hasNext()) {
            sb.append(d_iter.next())
        }
        response?.end(sb.toString()) // TODO
    }

}
