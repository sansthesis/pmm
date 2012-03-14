(ns pmm.mock
  (:require [clj-http.client :as client]))
  
(defn load-mock-data
  [] (dotimes [i 15]
       (client/post
         (str "http://localhost:8080/customers")
         {:form-params {:firstname (str "Bob " i)
                        :lastname "Bobber"
                        :email (str "bob.bobber" i "@test.com")
                        :zipcode "85251"}
          :throw-exceptions false})))
