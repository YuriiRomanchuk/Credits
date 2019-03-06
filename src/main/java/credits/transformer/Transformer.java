package credits.transformer;

import javax.servlet.http.HttpServletRequest;

public interface Transformer<V> {

    V transformToObject(HttpServletRequest request);
}
