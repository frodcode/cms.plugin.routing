package frod.routing.service

import static org.junit.Assert.*
import org.junit.*
import frod.routing.domain.Domain
import frod.routing.domain.HttpMethodEnum
import frod.routing.domain.PageType
import frod.routing.domain.Page
import frod.routing.domain.RequestTypeEnum
import frod.routing.domain.UrlTypeEnum

import frod.routing.domain.Redirect
import org.codehaus.groovy.grails.web.binding.DataBindingUtils

class PageServiceTests {

    PageService pageService

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear dopawn logic here
    }

    Domain getTestDomain() {
        return Domain.findAll()[0]
    }

    PageType getTestPageType() {
        String slug = 'test_page_type_for_tests'
        PageType pageType = PageType.findBySlug(slug)
        if (!pageType) {
            pageType = new PageType(
                    slug: slug,
                    description: 'Test page type',
                    singleton: false,
                    controller: 'Test',
                    action: 'list')
            pageType.save(flush: true)
        }
        return pageType;
    }

    Page getTestRoot() {
        return Page.findAllByParentIsNull()[0]
    }

    private PageCommand getTestingPageCommand() {
        PageCommand pageCommand = new PageCommand(
                domainId: getTestDomain().id,
                httpMethod: HttpMethodEnum.GET,
                langPart: '',
                pageTypeId: getTestPageType().id,
                parentId: getTestRoot().id,
                requestType: RequestTypeEnum.REGULAR,
                urlPart: '/testing-page-in-test',
                urlType: UrlTypeEnum.FROM_PARENT
        )

        return pageCommand;
    }

    @Test
    void testCreatePageFromRoot() {
        PageCommand pageCommand = getTestingPageCommand()
        Page page = pageService.createPage(pageCommand)
        assertNotNull(page.id)
        assertEquals(getTestDomain().url + pageCommand.urlPart, page.url)
        assertEquals(getTestDomain().id, page.domainId)
        assertEquals(HttpMethodEnum.GET, page.httpMethod)
        assertNull(page.langPart)
        assertEquals(getTestPageType().id, page.pageTypeId)
        assertEquals(getTestRoot().id, page.parentId)

        PageCommand subPageCommand = new PageCommand(
                domainId: getTestDomain().id,
                httpMethod: HttpMethodEnum.GET,
                langPart: '',
                pageTypeId: getTestPageType().id,
                parentId: page.id,
                requestType: RequestTypeEnum.REGULAR,
                urlPart: '/second-level',
                urlType: UrlTypeEnum.FROM_PARENT
        )
        Page subPage = pageService.createPage(subPageCommand)
        assertNotNull(subPage.id)
        assertEquals(page.url + subPageCommand.urlPart, subPage.url)
        assertEquals(getTestDomain().id, subPage.domainId)
        assertEquals(HttpMethodEnum.GET, subPage.httpMethod)
        assertNull(subPage.langPart)
        assertEquals(getTestPageType().id, subPage.pageTypeId)
        assertEquals(page.id, subPage.parentId)
    }

    @Test
    void testEditPage() {
        PageCommand pageCommandCreate = getTestingPageCommand()
        Page page = pageService.createPage(pageCommandCreate)
        assertNull(Redirect.findByFromUrl(page.url))
        String oldUrl = page.url
        PageCommand pageCommand = new PageCommand(
                pageId:  page.id,
                domainId: getTestDomain().id,
                httpMethod: HttpMethodEnum.GET,
                langPart: '',
                pageTypeId: getTestPageType().id,
                parentId: getTestRoot().id,
                requestType: RequestTypeEnum.REGULAR,
                urlPart: '/updated-testing-page',
                urlType: UrlTypeEnum.FROM_PARENT
        )
        pageService.editPage(pageCommand)
        assertEquals(getTestDomain().url + pageCommand.urlPart, page.url)
        assertEquals(getTestDomain().id, page.domainId)
        assertEquals(HttpMethodEnum.GET, page.httpMethod)
        assertNull(page.langPart)
        assertEquals(getTestPageType().id, page.pageTypeId)
        assertEquals(getTestRoot().id, page.parentId)

        Redirect redirect = Redirect.findByFromUrl(oldUrl)
        assertNotNull(redirect)
        assertEquals(redirect.toUrl, page.url)
    }

    @Test
    void testEditLangPageChangeType() {
        Page subArticleCzPage = Page.findByUrlPart('/sub-article-1-cz')
        Page articleCzPage = Page.findByUrlPart('/article-1-cz')
        String oldUrl = subArticleCzPage.url
        assertNull(Redirect.findByFromUrl(oldUrl))

        assertEquals(articleCzPage.url + subArticleCzPage.urlPart, subArticleCzPage.url)
        PageCommand pageCommand = new PageCommand(
                pageTypeId: subArticleCzPage.pageTypeId,
                urlPart: subArticleCzPage.urlPart,
                langPart: subArticleCzPage.langPart,
                pageId: subArticleCzPage.id,
                domainId: subArticleCzPage.domainId,
                urlType: UrlTypeEnum.FROM_ROOT,
                parentId:  subArticleCzPage.parentId
        )

        pageService.editPage(pageCommand)
        assertNotSame(articleCzPage.url + subArticleCzPage.urlPart, subArticleCzPage.url)
        assertEquals(subArticleCzPage.domain.url+subArticleCzPage.urlPart, subArticleCzPage.url)

        Redirect redirect =  Redirect.findByFromUrl(oldUrl)
        assertNotNull(redirect)
        assertEquals(redirect.toUrl, subArticleCzPage.url)
    }

    @Test
    void createSubLanguagePage() {
        Page parent = Page.findByUrlPart('/article-1-en')
        PageCommand newPageCommand = new PageCommand(
                parentId: parent.id,
                urlPart: '/sub-article-1-2-en',
                urlType: UrlTypeEnum.FROM_PARENT,
                pageTypeId: PageType.findBySlug('test_article').id
        )
        Page page = pageService.createPage(newPageCommand)
        assertEquals(parent.url + newPageCommand.urlPart, page.url)
        assertEquals(parent.domain, page.domain)
        assertEquals(page.requestType, RequestTypeEnum.REGULAR)
        assertEquals(page.httpMethod, HttpMethodEnum.GET)
    }

}
