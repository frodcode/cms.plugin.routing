package routing.data

import frod.routing.domain.PageType
import frod.routing.domain.Page
import frod.routing.domain.UrlTypeEnum
import frod.routing.domain.RequestTypeEnum
import frod.routing.domain.HttpMethodEnum

/**
 * User: freeman
 * Date: 5.7.13
 */
class TestData {

    public static def load(def ctx, def defaultDomain, def fixturesData) {
        def pageTypes = [
                homepagePageType: new PageType(
                        slug: 'test_homepage',
                        description: 'Test homepage',
                        singleton: true,
                        controller: 'TestHomepage',
                        action: 'index'),
                articlePageType: new PageType(
                        slug: 'test_article',
                        description: 'Homepage',
                        singleton: false,
                        controller: 'TestArticle',
                        action: 'index'),
        ]
        pageTypes*.value*.save(flush:true)
        def homepages = [
                homepage_cz: new Page(
                        domain: defaultDomain,
                        urlPart: '/',
                        urlType: UrlTypeEnum.ROOT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.homepagePageType
                ),
                homepage_en: new Page(
                        domain: defaultDomain,
                        urlPart: '/',
                        langPart: '/en',
                        urlType: UrlTypeEnum.ROOT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.homepagePageType
                ),
                homepage_de: new Page(
                        domain: defaultDomain,
                        urlPart: '/',
                        langPart: '/de',
                        urlType: UrlTypeEnum.ROOT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.homepagePageType
                ),
        ]

        homepages*.value*.save(flush: true)

        def articles = [
                article1_cz: new Page(
                        domain: defaultDomain,
                        urlPart: '/article-1-cz',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.articlePageType,
                        parent: homepages.homepage_cz,
                ),
                article2_cz: new Page(
                        domain: defaultDomain,
                        urlPart: '/article-2-cz',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.articlePageType,
                        parent: homepages.homepage_cz,
                ),
                article1_en: new Page(
                        domain: defaultDomain,
                        urlPart: '/article-1-en',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.articlePageType,
                        parent: homepages.homepage_en,
                ),
                article1_de: new Page(
                        domain: defaultDomain,
                        urlPart: '/article-1-de',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.articlePageType,
                        parent: homepages.homepage_de,
                ),
        ]

        articles*.value*.save(flush: true)

        def subArticles = [
                subarticle1_1_cz: new Page(
                        domain: defaultDomain,
                        urlPart: '/sub-article-1-cz',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.articlePageType,
                        parent: articles.article1_cz,
                ),
                subarticle1_1_en: new Page(
                        domain: defaultDomain,
                        urlPart: '/sub-article-1-en',
                        urlType: UrlTypeEnum.FROM_PARENT,
                        requestType: RequestTypeEnum.REGULAR,
                        httpMethod: HttpMethodEnum.GET,
                        pageType: pageTypes.articlePageType,
                        parent: articles.article1_en,
                ),
        ]
        subArticles*.value*.save(flush: true)
    }
}
