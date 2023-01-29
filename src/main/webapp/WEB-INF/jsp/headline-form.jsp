<%@ page import="com.aina.headlines.model.HeadlineType" %>
<%@ page import="java.util.List" %>
<%@ page import="com.aina.headlines.model.Headline" %><%--
  Created by IntelliJ IDEA.
  User: aina
  Date: 2023-01-29
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  List<HeadlineType> types = (List<HeadlineType>) request.getAttribute("types");
%>
<%@ include file="header.jsp" %>
  <div class="container">
    <h3>Create headline</h3>
    <div class="card card-primary">
          <div class="card-header">
            <h3 class="card-title">Headline</h3>
          </div>
          <!-- /.card-header -->
          <!-- form start -->
          <form>
            <div class="card-body">
              <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" name="title" id="title" placeholder="Enter title" required>
              </div>
              <div class="form-group">
                <label>Type</label>
                <select name="type" class="form-control">
                  <% for(HeadlineType type : types) { %>
                    <option value="<%= type.getId() %>"><%= type.getName() %></option>
                  <% } %>
                </select>
              </div>
              <div class="form-group">
                <label for="picture">Picture</label>
                <div class="input-group">
                  <div class="custom-file">
                    <input type="file" class="custom-file-input" id="picture">
                    <label class="custom-file-label" for="picture">Choose file</label>
                  </div>
                  <div class="input-group-append">
                    <span class="input-group-text">Upload</span>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label for="date1">Date 1</label>
                <input type="date" class="form-control" name="date1" id="date1" required>
              </div>
              <div class="form-group">
                <label for="date2">Date 2</label>
                <input type="date" class="form-control" name="date2" id="date2" required>
              </div>
              <div class="form-group">
                <label for="place">Place</label>
                <input type="text" class="form-control" name="place" id="place" placeholder="Enter place" required>
              </div>
              <div class="form-group">
                <label for="body">Body</label>
                <textarea type="date" class="form-control" name="body" id="body" required >
                </textarea>
              </div>
            </div>
            <!-- /.card-body -->

            <div class="card-footer">
              <button type="submit" class="btn btn-primary">Submit</button>
            </div>
          </form>
        </div>
  </div>
<%@ include file="footer.jsp" %>
