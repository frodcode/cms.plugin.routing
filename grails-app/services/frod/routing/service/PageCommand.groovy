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

    Integer pageId

    String urlPart

    String langPart

    UrlTypeEnum urlType

    Long pageTypeId

    HttpMethodEnum httpMethod = HttpMethodEnum.GET

    RequestTypeEnum requestType = RequestTypeEnum.REGULAR

    Long parentId

    Long domainId

    static constraints = {
        importFrom Page
        pageTypeId(validator: {val, obj -> obj.getPageType() != null})
        pageId(nullable: true, validator: { val, obj ->
            if (obj.pageId) {
                return !!Page.get(obj.pageId)
            }
            return true;
        })
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

    public Page getPage() {
        if (!pageId) {
            throw new IllegalStateException('Cannot get page because no id is available')
        }
        return Page.get(pageId)
    }

}
