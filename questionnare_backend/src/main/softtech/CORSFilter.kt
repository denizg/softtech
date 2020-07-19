
package main.softtech

import java.io.IOException
import javax.servlet.Filter;
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CORSFilter : Filter {

  override fun destroy() {
    // TODO Auto-generated method stub
  }

  @Throws(IOException::class, ServletException::class)
  override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, chain: FilterChain) {
    val request = servletRequest as HttpServletRequest
    (servletResponse as HttpServletResponse).addHeader("Access-Control-Allow-Origin", "*")
    servletResponse.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST")
    servletResponse.addHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers")
    if (request.method == "OPTIONS") {
      servletResponse.setStatus(HttpServletResponse.SC_ACCEPTED)
      return
    }
    chain.doFilter(request, servletResponse)
  }

  override fun init(fConfig: FilterConfig?) {
    // TODO Auto-generated method stub
  }
}
