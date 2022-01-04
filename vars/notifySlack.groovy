#!/usr/bin/env groovy

def call(String buildStatus = 'STARTED') {
    buildStatus = buildStatus ?: 'SUCCESSFUL'

    def imageurl = 'https://www.jenkins.io/images/logos/fire/fire.png'
    def msg = "Your job failed:"
    def subject = "*${msg}\n<${env.BUILD_URL}|Build details>"
    def section = "*Job name:*\nl${env.JOB_NAME}\n*Build number:*\n${env.BUILD_NUMBER}"

    if (buildStatus == 'STARTED') {
        msg = "Your job started:"
        imageurl = 'https://www.jenkins.io/images/logos/formal/formal.png'
    } else if (buildStatus == 'SUCCESSFUL') {
        msg= "Your job finished successfully:"
        imageurl = 'https://www.jenkins.io/images/logos/cute/cute.png'
    } else {
        msg = "Your job failed:"
        imageurl = 'https://www.jenkins.io/images/logos/fire/fire.png'
    }

    subject = "*${msg}\n<${env.BUILD_URL}|Build details>"
    
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
                "text": section
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
