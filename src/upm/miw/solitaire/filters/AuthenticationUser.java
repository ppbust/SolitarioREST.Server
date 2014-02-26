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

@WebFilter("/security/user/*")
public class AuthenticationUser implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LoginBean loginBean = (LoginBean) ((HttpServletRequest) request).getSession().getAttribute(
                "loginBean");
        if (loginBean == null || !loginBean.isLoggedUser())
            ((HttpServletResponse) response).sendRedirect("../../error.xhtml");
        else
            chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
