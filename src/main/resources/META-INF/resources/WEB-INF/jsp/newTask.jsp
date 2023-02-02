<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf" %>
<div class="container">
    <h3>Add a new Task</h3>
    <%--@elvariable id="task" type="bean"--%>
    <form:form method="post" modelAttribute="task">
        <div class="form-group">
            <fieldset class="mb-3">
                <form:label path="description">Description:</form:label>
                <form:input type="text" class="form-control" id="description" path="description"
                            placeholder="Enter description" required="required" cssClass="align-items-center"/>
                <form:errors path="description" cssClass="alert-warning"/>
            </fieldset>
            <fieldset class="mb-3">
                <form:label path="completionDate">Completion Date:</form:label>
                <form:input type="text" class="form-control" id="completionDate" path="completionDate"
                            cssClass="align-items-center"/>
                <form:errors path="completionDate" cssClass="alert-warning"/>
            </fieldset>
            <fieldset class="mb-3">
                <form:label path="complete">is it complete?</form:label>
                <form:checkbox path="complete"/>
            </fieldset>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>
</div>

<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
    $('#completionDate').datepicker({
        format: 'yyyy/mm/dd',
        startDate: '-3d'
    });
</script>

</body>
</html>