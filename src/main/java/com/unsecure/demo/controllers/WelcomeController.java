package com.unsecure.demo.controllers;

import com.unsecure.demo.dao.LoginDao;
import com.unsecure.demo.formbeans.LoginForm;
import com.unsecure.demo.utilities.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeController extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        LoginDao loginDao = new LoginDao();
        LoginForm loginForm = (LoginForm) form;
        if (StringUtils.isEmpty(loginForm.getUserName())) {
            throw new Exception("Username is invalid");
        }
        if (StringUtils.isEmpty(loginForm.getPassword())) {
            throw new Exception("Password is invalid");
        }

        if(StringUtils.isNotEmpty(loginForm.getUserName())){
            logger.debug(loginForm.getUserName());
        }
        if(StringUtils.isNotEmpty(loginForm.getPassword())){
            logger.debug(loginForm.getPassword());
        }

        if (loginDao.checkUserNameAndPassword(loginForm.getUserName(), loginForm.getPassword())) {
            return mapping.findForward(Constants.SUCCESS);
        } else {
            return mapping.findForward(Constants.FAILURE);
        }
    }
}