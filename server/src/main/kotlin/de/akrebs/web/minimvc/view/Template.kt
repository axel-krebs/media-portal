package de.akrebs.web.minimvc.view

import de.akrebs.web.minimvc.model.ModelBase
import io.vertx.core.http.HttpServerResponse

open class Template(format: Format, data: Sequence<Char>) {

    val d_iter = data.iterator()

    fun apply(model: ModelBase, response: HttpServerResponse?) {
        val sb : StringBuilder = StringBuilder()
        while(d_iter.hasNext()) {
            sb.append(d_iter.next())
        }
        response?.end(sb.toString()) // TODO
    }

}
