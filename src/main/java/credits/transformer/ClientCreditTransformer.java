package credits.transformer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ClientCreditTransformer implements Transformer {
    @Override
    public Map<String, String> transformToObject(HttpServletRequest request) {

        Map<String, String> selections = new HashMap<>();
        selections.put("client_id", request.getParameter("client_id"));
        selections.put("credit_line_id", request.getParameter("choose-credit-line"));
        return selections;
    }
}
