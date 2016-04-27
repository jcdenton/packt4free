#!/usr/bin/env bash

# Only upload artifacts from upstream's master build on Oracle JDK 7
if [[ ${TRAVIS_PULL_REQUEST} == "false" ]] && [[ ${TRAVIS_BRANCH} == "master" ]] && [[ ${JAVA_HOME} == "/usr/lib/jvm/java-8-oracle" ]]; then
	if grep -q "SNAPSHOT" gradle.properties; then
	else
		echo "Uploading RELEASE to https://bintray.com/"
		./gradlew bintrayUpload
	fi
fi
