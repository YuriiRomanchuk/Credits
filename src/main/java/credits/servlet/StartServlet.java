package credits.servlet;

import credits.initializer.PrimaryInitializer;
import credits.view.RedirectViewModel;
import credits.view.View;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class StartServlet extends HttpServlet {

    private PrimaryInitializer primaryInitializer;

    @Override
    public void init() throws ServletException {
        primaryInitializer = new PrimaryInitializer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reference(getView(req, primaryInitializer.getGetControllers()), req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        reference(getView(req, primaryInitializer.getPostControllers()), req, resp);
    }


    private void reference(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (view instanceof RedirectViewModel) {
            request.getSession().setAttribute(PrimaryInitializer.getModelViewAttribute(), view.getView());
            response.sendRedirect(view.getPageUrl());
        } else if (view != null) {
            view.getParameters().forEach(request::setAttribute);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/" + view.getPageUrl());
            requestDispatcher.forward(request, response);
        }
    }

    private View getView(HttpServletRequest request, Map<String, Function<HttpServletRequest, View>> sourceController) {
        String requestURI = request.getRequestURI().replace(request.getContextPath() + "/main", "");

        View originView = (View) request.getSession().getAttribute(PrimaryInitializer.getModelViewAttribute());
        request.getSession().removeAttribute(PrimaryInitializer.getModelViewAttribute());

        View destinationView = Optional.ofNullable(sourceController.get(requestURI)).map(f -> f.apply(request)).orElse(null);
        if (originView != null && destinationView != null) {
            originView.getParameters().forEach(destinationView::addParameter);
        }
        return destinationView;
    }
}
