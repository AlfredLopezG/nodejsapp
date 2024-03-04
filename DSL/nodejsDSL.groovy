job('Aplicacion nodeJS DSL hijo') {
    description('Aplicación Node JS DSL hijo para el curso de Jenkins')
    scm {
        git('https://github.com/AlfredLopezG/nodejsapp.git', 'master') { node ->
            node / gitConfigName('AlfredLopezG')
            node / gitConfigEmail('lopezg.alfred@gmail.com')
        }
    }
    triggers {
        scm('H/7 * * * *')
    }
    wrappers {
        nodejs('nodeVersion01')
    }
    steps {
        shell("npm install")
    }
    publishers {
	slackNotifier {
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(true)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            customMessage(null)
            sendAs(null)
            commitInfoChoice('NONE')
            teamDomain(null)
            authToken(null)
        }
    }
}
