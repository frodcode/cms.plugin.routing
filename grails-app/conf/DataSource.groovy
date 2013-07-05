dataSource {
    pooled = false
    //driverClassName = "org.h2.Driver"
	//username = "sa"
	//password = ""

	driverClassName = "org.postgresql.Driver"
	username = "postgres"
	password = "root"

}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            //url = "jdbc:h2:file:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
			url = "jdbc:postgresql://localhost:5432/routing"
        }
    }
    test {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:postgresql://localhost:5432/routing"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
