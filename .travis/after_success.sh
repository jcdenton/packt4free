#!/usr/bin/env bash

# Only upload tagged artifacts build on Oracle JDK 7
if [[ ! -z ${TRAVIS_TAG} ]] && [[ ${JAVA_HOME} == "/usr/lib/jvm/java-8-oracle" ]]; then
	echo "Uploading RELEASE to https://bintray.com/"
	./gradlew bintrayUpload
fi
