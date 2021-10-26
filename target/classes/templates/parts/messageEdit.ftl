
<a class="btn btn-primary" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Message Editor
</a>
<div class="row collapse <#if message??>show</#if>" id="collapseExample">
    <form method="post" enctype="multipart/form-data">
        <div class="input-group mt-3">
            <input class="form-control ${(textError??)?string('is-invalid','')}" type ="text"
                   value = "<#if message??>${message.text}</#if>" name="text" placeholder="Enter text"/>
            <#if textError??>
                <div class="invalid-feedback">
                    ${textError}
                </div>
            </#if>
        </div>
        <div class="input-group mt-3">
            <input class="form-control ${(tagError??)?string('is-invalid','')}" type ="text"
                   value = "<#if message??>${message.tag}</#if>" name="tag" placeholder="Enter tag"/>
            <#if tagError??>
                <div class="invalid-feedback">
                    ${tagError}
                </div>
            </#if>
        </div>
        <div class="input-group mt-3">
            <input type="file" name="file" id="customfile" class="form-control">
            <label for = "customfile" class="input-group-text">Choose file</label>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="hidden" name="id" value = "<#if message??>${message.id}</#if>" />
        <div class="input-group mt-3">
            <button type="submit" class="btn btn-primary form-control">Save message</button>
        </div>
    </form>
</div>
