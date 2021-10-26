<#import "parts/common.ftl" as c>

<@c.page>
    <div class="row row-cols-lg-auto g-3 align-items-center">
        <div class="col-2">
            <form method="get" action="/main">
                <input type="text" name="filter" value="${filter?ifExists}" placeholder="Search by message">
                <button type="submit" class="btn btn-primary ms-2">Search</button>
            </form>
        </div>
    </div>
    <#include "parts/messageEdit.ftl" />
    <#include "parts/messageList.ftl" />

</@c.page>