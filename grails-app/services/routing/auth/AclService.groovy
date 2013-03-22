package routing.auth

import routing.RoutingService
import routing.domain.Page

class AclService {

    def adminHomepageSlug

    def notLoggedInSlug

    def notEnoughPrivilegesSlug

    RoutingService routingService

    public IAclAction getRedirectAddressOnNotLoggedIn(Page page)
    {
        if (page.pageType.getRoot().slug == this.adminHomepageSlug) {
            return new AclRedirectAction(routingService.getSingleton(this.notLoggedInSlug).url)
        }
        println page.pageType.getRoot().slug + ' - root slug'
        println this.adminHomepageSlug +' - redirect slug'
        throw new IllegalArgumentException('Cannot find redirect page for non authorized acces to url '+page.url);
    }

    public IAclAction getActionOnNotEnoughPrivileges(Page page)
    {
        return new AclRedirectAction(notEnoughPrivilegesSlug);
    }
}
