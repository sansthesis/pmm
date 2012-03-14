(ns praxis.data
  (:require [clojure.java.jdbc :as jdbc]))

(defn list-query
  ([ds query] (list-query ds query identity))
  ([ds query converter]
    (jdbc/with-connection
      ds
      (jdbc/transaction
        (jdbc/with-query-results rs
                                 (if (vector? query) query [query])
                                 (doall (map converter rs)))))))

(defn get-query
  ([ds query] (get-query ds query identity))
  ([ds query converter]
    (first (list-query ds query converter))))

(defn create
  ([ds table record]
    (println "Inserting into '" table "':" record)
    (jdbc/with-connection
      ds
      (jdbc/transaction
        (jdbc/insert-record table record)))
    record))
