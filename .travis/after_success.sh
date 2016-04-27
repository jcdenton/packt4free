#!/bin/sh

# Only upload artifacts from upstream's master build on Oracle JDK 7
if [[ ${TRAVIS_PULL_REQUEST} == "false" ]] && [[ ${TRAVIS_BRANCH} == "master" ]] && [[ ${JAVA_HOME} == "/usr/lib/jvm/java-8-oracle" ]]; then
	./gradlew bintrayUpload
fi
