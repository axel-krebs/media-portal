package com.afrtn.portal.frontend

import minimvc.controller.FormDataController
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@ApplicationScoped
class LoginController : FormDataController() {

    @Inject
    @field:Default
    lateinit var loginModel: LoginModel;

}