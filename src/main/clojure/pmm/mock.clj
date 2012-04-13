(ns pmm.mock
  (:require [clj-http.client :as client]))
  
(defn load-mock-data
  [] (dotimes [i 5]
       (client/post
         (str "http://localhost:8080/contacts")
         {:form-params {:firstname (str "Bob " i)
                        :lastname "Bobber"
                        :email (str "bob.bobber" i "@test.com")
                        :zipcode "85251"}
          :throw-exceptions false}))
  (dotimes [i 5]
       (client/post
         (str "http://localhost:8080/organizations")
         {:form-params {:name (str "Organization " i)}
          :throw-exceptions false})
       (dotimes [j 3]
         (client/post
           (str "http://localhost:8080/organizations/" i "/divisions")
           {:form-params {:name (str "Division " i " - " j)
                          :number (str i " - " j)}
            :throw-exceptions false}))))
