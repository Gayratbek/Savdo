<#import "parts/common.ftl" as c>
<@c.page>
    <h5>${username}</h5>
    ${message?ifExists}
    <form method="post">
        <div class="mb-3 row">
            <label class="col-sm-2 col-form-label"> Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control-plaintext" placeholder="Password"/>
            </div>
        </div>
            <div class="mb-3 row">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control-plaintext"
                           placeholder="some@some.com" value="${email!''}"/>
                </div>
            </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary mb-3" type="submit" >Save</button>
    </form>

</@c.page>