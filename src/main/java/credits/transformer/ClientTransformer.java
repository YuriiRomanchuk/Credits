package credits.transformer;

import credits.data.model.Client;

import javax.servlet.http.HttpServletRequest;

public class ClientTransformer implements Transformer {
    @Override
    public Client transformToObject(HttpServletRequest request) {
        Client client = new Client();
        client.setName(request.getParameter("name"));
        return client;
    }
}
