package frod.routing.service

import frod.routing.domain.UrlTypeEnum
import frod.routing.domain.HttpMethodEnum
import frod.routing.domain.RequestTypeEnum
import frod.routing.domain.Page
import frod.routing.domain.PageType
import frod.routing.domain.Domain

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

    public Domain getDomain() {
        return Domain.get(domainId)
    }

}
