package frod.routing.service

import ford.routing.domain.UrlTypeEnum
import ford.routing.domain.HttpMethodEnum
import ford.routing.domain.RequestTypeEnum
import ford.routing.domain.Page
import ford.routing.domain.PageType
import ford.routing.domain.Domain

/**
 * User: freeman
 * Date: 28.6.13
 */
@grails.validation.Validateable
class PageCommand {
    String urlPart

    String langPart

    UrlTypeEnum urlType

    Long pageTypeId

    HttpMethodEnum httpMethod

    RequestTypeEnum requestType

    Long parentId

    Long domainId

    static constraints = {
        // todo dodelat
        importFrom Page
        pageTypeId(validator: {val, obj -> obj.getPageType() != null})
    }

    public PageType getPageType() {
        return PageType.get(pageTypeId)
    }

    public Page getParent() {
        return Page.get(parentId)
    }

    public Page getDomain() {
        return Domain.get(domainId)
    }

}
