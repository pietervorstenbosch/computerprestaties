FROM ubuntu:20.04

RUN apt update && \
    apt install -y software-properties-common && \
    add-apt-repository ppa:deadsnakes/ppa && \
    apt update && \
    apt install -y python3.8 && \
    apt install -y openjdk-11-jdk && \
    apt install -y build-essential

RUN mkdir /opt/scripts

WORKDIR /opt/scripts

COPY ./scripts/* .
