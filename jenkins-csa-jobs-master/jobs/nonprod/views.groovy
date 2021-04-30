 views {
        listView('All') {
        description('')
        jobs {
            name ('admin-seedjob')
            regex(/csa-.*/)
				  }
          columns {
            status()
            weather()
            name()
            lastSuccess()
            lastFailure()
            lastDuration()
            buildButton()
          }
        }
 }
