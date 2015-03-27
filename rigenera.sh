#!/bin/bash
# Rigenera da sorgenti

./grailsw stop-app
./grailsw clean-all
./grailsw refresh-dependencies
./grailsw run-app
