FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/cli-admin-1.1.1.jar cli-admin-1.1.1.jar
ENV JAVA_HOME=/jdk8
ENV PATH=$JAVA_HOME/bin:$PATH
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/cli-admin-1.1.1.jar"]