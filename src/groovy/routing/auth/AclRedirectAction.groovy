package routing.auth

/**
 * User: freeman
 * Date: 22.3.13
 */
class AclRedirectAction implements IAclAction {

    String url;

    public AclRedirectAction(String url)
    {
            this.url = url
    }

}
