#!/usr/bin/env groovy

def call(String msg = 'status is unknown.') {
    slackSend(color: "good", message: "Your build ${msg}.")
}