package com.afrtn.portal.frontend

import minimvc.model.FormDataModel
import javax.enterprise.context.Dependent
import javax.enterprise.context.SessionScoped

@SessionScoped
@Dependent
class LoginModel : FormDataModel() {

    lateinit var name: String
    lateinit var password: String

    override fun apply(map: Map<String, Any>) {
        map.get("name")
    }


    override fun isValid(): Boolean {
        TODO("Not yet implemented")
    }

}
