job('Aplicacion Node.js Docker DSL Hijo') {
    description('Aplicación Node JS Docker DSL Hijo para el curso de Jenkins')
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
        dockerBuildAndPublish {
            repositoryName('alfredlg/nodejsdsl')
            tag('${GIT_REVISION,length=7}')
            registryCredentials('alfredlg')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
    publishers {
    	/*Se remueve porque no se tiene cuenta slack y marca error
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
        }*/
    }
}
