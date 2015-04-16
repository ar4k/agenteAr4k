#!/bin/bash
vncserver -geometry 1024x992 -depth 8
./launch.sh --vnc localhost:5903
