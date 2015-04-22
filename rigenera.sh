#!/bin/bash
# Rigenera da sorgenti

./grailsw stop-app
./grailsw clean-all
./grailsw refresh-dependencies
./grailsw doc
./grailsw test-app
#./grailsw run-app
