package credits.transformer;

import credits.model.Client;

import javax.servlet.http.HttpServletRequest;

public class ClientTransformer implements Transformer {
    @Override
    public Client transformToObject(HttpServletRequest request) {
        Client client = new Client();
        client.setName(request.getParameter("name"));
        return client;
    }
}
