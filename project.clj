(defproject pmm "1.0.0-SNAPSHOT"
  :description "PMM."
  :source-path "src/main/clojure"
  :compile-path "target/classes"
  :resources-path "src/main/resources"
  :test-path "src/test/clojure"
  :library-path "target/lib"
  :warn-on-reflection false
  :main pmm.server
  :repl-init pmm.server
  :hooks [leiningen.cljsbuild]
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [cheshire "3.0.0"] ; To override the version specified in noir
                 [noir "1.2.1"]
                 [clj-http "0.3.3"]
                 [org.clojure/java.jdbc "0.1.3"]
                 [org.clojure/data.json "0.1.2"]
                 [org.hsqldb/hsqldb "2.2.4"]
                 [com.atomikos/transactions-jta "3.7.0"]
                 [com.atomikos/transactions-jdbc "3.7.0"]]
  :plugins [[lein-cljsbuild "0.1.6"]]
  :cljsbuild {:builds [{:source-path "src/main/cljs"
                        :compiler {:output-to "target/classes/public/static/script/js.js"
                                   :output-dir "target/cljs-work"
                                   :jar true
                                   :optimizations :whitespace
                                   :pretty-print true}}]})
