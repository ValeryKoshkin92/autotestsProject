<#-- my-http-request.ftl -->
<div>
    <p><b>Method:</b> <code>${data.method}</code> <b>URL:</b> <code>${data.url}</code></p>

    <#if data.body??>
        <p><b>Body:</b></p>
        <pre>${data.body}</pre>
    </#if>

    <#if data.headers?? && data.headers?size gt 0>
        <p><b>Headers:</b></p>
        <pre><#list data.headers as name, value>${name}: ${value}
</#list></pre>
    </#if>

    <#if data.curl??>
        <p><b>cURL:</b></p>
        <pre>${data.curl}</pre>
    </#if>
</div>