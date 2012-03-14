(ns pmm.server
  (:require [noir.server :as server]
            [pmm.ds :as ds]
            [pmm.mock :as mock]))

(server/load-views "src/main/clojure/pmm/views/")

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (server/start port {:mode mode
                        :ns 'pmm}))
  (mock/load-mock-data))

(defn main [& m]
  (-main m))
