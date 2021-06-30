# In case you are not interested in multithreading with opencilk you can use
# FROM ubuntu:20.04
FROM opencilk:v1.0

RUN apt update && \
    apt install -y software-properties-common && \
    add-apt-repository ppa:deadsnakes/ppa && \
    apt update && \
    apt install -y python3.8 && \
    apt install -y openjdk-11-jdk && \
    apt install -y build-essential && \
    apt install -y vim

RUN mkdir /opt/scripts

WORKDIR /opt/scripts

COPY ./scripts/test-1 ./test-1
COPY ./scripts/test-2 ./test-2
COPY ./scripts/test-3 ./test-3
COPY ./scripts/test-4 ./test-4
