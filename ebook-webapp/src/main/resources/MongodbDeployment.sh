#!/usr/bin/env bash

export MONGODB_VERSION=8.0.0-rc9-jammy
export MONGODB_PORT=27000
docker run --name mongodb -d -p $MONGODB_PORT:27017 mongo:$MONGODB_VERSION