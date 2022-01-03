#!/usr/bin/env groovy

def call(String buildStatus = 'STARTED') {
    buildStatus = buildStatus ?: 'SUCCESSFUL'

    def colorName = 'RED'
    def colorCode = '#FF0000'
    def imageurl = 'https://www.jenkins.io/images/logos/fire/fire.png'
    def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    def buildurl = "${env.BUILD_URL}"

    if (buildStatus == 'STARTED') {
        color = 'YELLOW'
        colorCode = '#FFFF00'
        imageurl = 'https://www.jenkins.io/images/logos/formal/formal.png'
    } else if (buildStatus == 'SUCCESSFUL') {
        color = 'GREEN'
        colorCode = '#00FF00'
        imageurl = 'https://www.jenkins.io/images/logos/cute/cute.png'
    } else {
        color = 'RED'
        colorCode = '#FF0000'
        imageurl = 'https://www.jenkins.io/images/logos/fire/fire.png'
    }

    blocks = [
        {
            "type": "section",
            "text": {
                "type": "mrkdown",
                "text": subject
            }
        },
        {
            "type": "section",
            "text": {
                "type": "mrkdwn",
                "text": buildurl
            },
            "accessory": {
                "type": "image",
                "image_url": imageurl,
                "alt_text": "Jenkins Artwork"
            }
        }
    ]

    slackSend(color: colorCode, blocks: blocks)
}