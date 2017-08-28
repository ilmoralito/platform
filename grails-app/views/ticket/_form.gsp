<div class="form-group">
    <label for="subject">Asunto</label>
    <g:textField name="subject" value="${ticket?.subject}" list="ticketIssuesList" class="form-control"/>
</div>

<datalist id="ticketIssuesList">
    <g:each in="${ticketIssuesList}" var="ticket">
        <option value="${ticket.subject}"></option>
    </g:each>
</datalist>
