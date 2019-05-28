FROM java:8
EXPOSE 8080

VOLUME /tmp
ADD /dbblog-search/target/dbblog.jar /app.jar
RUN bash -c 'touch /app.jar'
RUN /sbin/ip route|awk '/default/ { print  $3,"\tdockerhost" }' >> /etc/hosts
ENTRYPOINT ["java","-jar","/app.jar"]
