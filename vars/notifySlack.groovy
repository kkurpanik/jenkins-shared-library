#!/usr/bin/env groovy

def call(String buildStatus = 'STARTED') {
    buildStatus = buildStatus ?: 'SUCCESSFUL'

    def imageurl = 'https://www.jenkins.io/images/logos/fire/fire.png'
    def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    def buildurl = "${env.BUILD_URL}"

    if (buildStatus == 'STARTED') {
        imageurl = 'https://www.jenkins.io/images/logos/formal/formal.png'
    } else if (buildStatus == 'SUCCESSFUL') {
        imageurl = 'https://www.jenkins.io/images/logos/cute/cute.png'
    } else {
        imageurl = 'https://www.jenkins.io/images/logos/fire/fire.png'
    }

    blocks = [
        [
            "type": "section",
            "text": [
                "type": "mrkdwn",
                "text": subject
            ]
        ],
        [
            "type": "section",
            "text": [
                "type": "mrkdwn",
                "text": buildurl
            ],
            "accessory": [
                "type": "image",
                "image_url": imageurl,
                "alt_text": "Jenkins Artwork"
            ]
        ]
    ]

    slackSend(blocks: blocks)
}