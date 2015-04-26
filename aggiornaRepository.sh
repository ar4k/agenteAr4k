#!/bin/bash
# Salva il contenuto del repository in git

echo "Aggiorno la documentazione online"
./grailsw doc
echo "git add ."
git add .
echo "git commit -a -m \"$1\""
git commit -a -m "$1"
echo "git push"
unset DISPLAY
git push
echo "aggiorna documentazione web su GitHub"
git push -f origin master:gh-pages

exit 0
