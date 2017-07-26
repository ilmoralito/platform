<g:hasErrors bean="${errors}">
    <g:eachError bean="${errors}" var="error">
        <g:if test="${error in org.springframework.validation.FieldError}">
            <li>
                <g:message error="${error}"/>
            </li>
        </g:if>
    </g:eachError>
</g:hasErrors>
