<#-- my-http-response.ftl -->
<div>
    <p><b>Status:</b> <code>${data.responseCode!"?"}</code></p>

    <#if data.body??>
        <p><b>Body:</b></p>
        <pre>${data.body}</pre>
    </#if>

<#if data.headers?? && data.headers?size gt 0>
    <p><b>Headers:</b></p>
    <pre><#list data.headers as name, value>${name}: ${value}
</#list></pre>
</#if>
</div>