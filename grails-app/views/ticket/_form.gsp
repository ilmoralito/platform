<div class="form-group">
    <label for="subject">Asunto</label>
    <g:textField name="subject" value="${ticket?.subject}" list="ticketList" class="form-control"/>
</div>

<datalist id="ticketList">
    <g:each in="${ticketList}" var="ticket">
        <option value="${ticket.subject}"></option>
    </g:each>
</datalist>
