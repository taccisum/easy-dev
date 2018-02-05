package cn.tac.framework.easydev.web.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * @author tac
 * @since 1.0
 */
public class WebUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    private void init(String dateformat){
        Objects.requireNonNull(dateformat, "date format");
        mapper.setDateFormat(new SimpleDateFormat(dateformat));
    }

    /**
     * 返回一个内容为json的http响应
     */
    public static void writeJson(HttpServletResponse response, Object obj) throws IOException {
        String json = mapper.writeValueAsString(obj);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.getWriter().write(json);
    }

    /**
     * 判断某个请求是否为ajax请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        return request.getHeader("accept").contains("application/json");
    }
}
