<%@ page import="com.aina.headlines.model.Headline" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String word = (request.getAttribute("word") == null) ? "" : (String) request.getAttribute("word");
    Integer currPage = (Integer) request.getAttribute("page");
    Integer status = (Integer) request.getAttribute("status");
    Double nbrElementParPage = 6.0;
    int pageNumber = (int) Math.ceil((Long) request.getAttribute("totalNumber") / nbrElementParPage);
    List<Headline> headlines = (List<Headline>) request.getAttribute("headlines");
    String pageName = request.getAttribute("pageName") == null ? "bo" : (String) request.getAttribute("pageName");
%>


<div class="container">
<h3>Headlines</h3>
<div class="row ">
    <% for(Headline headline : headlines) { %>
    <div class="col-4">
        <div class="card">
            <img class="card-img-top" src="${pageContext.request.contextPath}/images/<%= headline.getPicture() %>" alt="Card image cap">
            <div class="card-body">
                <% if(headline.getHeadlineType().getId() == 1) { %>
                    <h5 class="card-title" style="color : blue"><%= headline.getTitle() %> </h5>
                <% } else { %>
                    <h5 class="card-title" ><%= headline.getTitle() %> </h5>
                <% } %>
                <p class="card-text"><%= headline.getBody() %> </p>
                <p> Type : <%= headline.getHeadlineType().getName() %> </p>
                <p> Date 1 : <%= headline.getDate1() %> </p>
                <p> Date creation : <%= headline.getDateCreation() %></p>
                <% if(headline.getHeadlineType().getId() == 1) { %>
                    <p> Date 2 : <%= headline.getDate2() %> </p>
                <% } %>
                <p> Place : <%= headline.getPlace() %> </p>
                <% if(headline.getDatePublication() == null) { %>
                    <form action="${pageContext.request.contextPath}/headline/<%= headline.getId() %>/datePublication" method="post" >
                        <p>
                            Date publication : <input type="datetime-local" name="datePubHtml" />
                            <input type="submit" vaue="Mettre a jour" />
                        </p>
                    </form>
                <% } else { %>
                    <%= headline.getDatePublication() %>
                <% } %>
                <% if(headline.getHeadlineStatusId() == 1){ %>
                    <a href="${pageContext.request.contextPath}/headline/<%= headline.getId() %>/publier"> Publier </a>
                <% }else{ %>
                    Deja publie
                <% } %>
            </div>
        </div>
    </div>
    <% } %>
</div>
<div class="row">
    <div class="col-md-4"> </div>
    <nav aria-label="..." class="col-md-4 mb-3">
        <ul class="pagination">
            <% for(int i = 1; i <= pageNumber ; i++) {
                String className = currPage == i ? "page-item active" : "page-item"; %>
            <li class="<%= className %>">
                <a class="page-link" href="${pageContext.request.contextPath}/<%= pageName %>/search?status=<%= status %>&word=<%= word %>&page=<%= i %>"><%= i %></a>
            </li>
            <% } %>
        </ul>
    </nav>
    <div class="col-md-4"></div>
</div>
</div>