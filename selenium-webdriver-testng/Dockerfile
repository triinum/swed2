FROM gradle:jdk16 AS build
ADD . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build test
