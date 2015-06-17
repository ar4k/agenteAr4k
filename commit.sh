#!/bin/bash
# Effettua un commit
#unset DISPLAY
unset SSH_ASKPASS

echo "git add ."
git add .
echo "git commit -a -m \"$1\""
git commit -a -m "$1"

exit 0
