package upm.miw.solitaire.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import upm.miw.solitaire.controllers.beans.LoginBean;

@WebFilter("/security/admin/*")
public class AuthenticationAdmin implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LoginBean loginBean = (LoginBean) ((HttpServletRequest) request).getSession().getAttribute(
                "loginBean");
        if (loginBean == null || !loginBean.isLogged() || !loginBean.isAdministrator())
            ((HttpServletResponse) response).sendRedirect("../../error.xhtml");
        else
            chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {
    }

}
