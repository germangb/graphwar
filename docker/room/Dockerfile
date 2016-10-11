FROM java:openjdk-8-jre-alpine
MAINTAINER @germangb <germangb42@gmail.com>

RUN mkdir /graphwar

COPY roomServer.jar /graphwar/room.jar

COPY run.sh /run.sh

RUN chmod +x /run.sh

CMD [ "/run.sh" ]
