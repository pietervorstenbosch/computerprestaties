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

COPY ./scripts/* .
