FROM java:8-jre
LABEL maintainer="983869078@qq.com"
RUN ["ls","-la"]
EXPOSE 8090
WORKDIR /usr/share
#ADD ./target/search-0.0.1-SNAPSHOT.jar app.jar
COPY search-0.0.1-SNAPSHOT.jar app.jar
VOLUME /usr/share
ENTRYPOINT ["java","-jar"]
CMD ["/app.jar"]