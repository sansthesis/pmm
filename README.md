## How to run:
   1. install leiningen (https://github.com/technomancy/leiningen)
   2. clone this repo
   3. run lein deps
   4. run lein run
   5. localhost:8080/

## Cygwin
   1. Install cygwin
   2. Install the modules: readline6, emacs, curl, wget, git
   3. in cygwin: cd /usr/local/bin && curl https://raw.github.com/technomancy/leiningen/stable/bin/lein >> lein && chmod 755 lein && lein self-install
   4. go to your cloned repo, or clone it if you didn't already
   5. lein deps && lein run OR lein deps && lein repl
   6. localhost:8080
