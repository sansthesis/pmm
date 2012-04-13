(defproject pmm "1.0.0-SNAPSHOT"
  :description "PMM."
;  :url "http://github.com/johntrimble/newman"
  :source-path "src/main/clojure"
  :compile-path "target/classes"
  :resources-path "src/main/resources"
  :test-path "src/test/clojure"
  :library-path "target/lib"
  :warn-on-reflection false
  :main pmm.server
  :repl-init pmm.server
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [cheshire "3.0.0"] ; To override the version specified in noir
                 [noir "1.2.2"]
                 [clj-http "0.3.3"]
                 [org.clojure/java.jdbc "0.1.3"]
                 [org.clojure/data.json "0.1.2"]
                 [org.hsqldb/hsqldb "2.2.4"]
                 [com.atomikos/transactions-jta "3.7.0"]
                 [com.atomikos/transactions-jdbc "3.7.0"]
                 ;[org.codehaus.jackson/jackson-core-asl "1.9.5"]
                 ;[org.codehaus.jackson/jackson-mapper-asl "1.9.5"]
                 ])
