<#include "security.ftl">

<div class="row row-cols-1 row-cols-md-3 g-4 mt-5" >
    <#list messages as message>
        <div class="card my-3">
            <#if message.filename??>
                <img src="/img/${message.filename}"  style="width:100px; height:50px" class="card-img-top"  />
            </#if>
            <div class="m-2">
                <span>${message.text}</span><br/>
                <i>#${message.tag}</i>
            </div>
            <div class="card-footer text-muted">
                <a href="/user-messages/${message.owner.id}">${message.ownerName}</a>
                <#if message.owner.id == currentUserId>
                    <a class="btn btn-primary" href="/user-messages/${message.owner.id}?message=${message.id}">
                        Edit
                    </a>
                </#if>
            </div>
        </div>
    <#else>
        No message
    </#list>
</div>