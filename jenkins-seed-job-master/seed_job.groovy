job('admin-seed-job') {
    description("admin seed job")
    scm{
        git{
            remote{
              url("")
              credentials("siva_credentials")
            }
            branch('*/main')
            
        }
    }
    steps {
        dsl {
           external('jobs/**/*.groovy')
           removeAction('DELETE')
           removeViewAction('DELETE')

        }
    }
}