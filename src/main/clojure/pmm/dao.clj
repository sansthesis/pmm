(ns pmm.dao
  (:require [clojure.java.jdbc :as jdbc]
            [pmm.ds :as ds]
            [praxis.data :as data]))

(defn list-contacts
  [] (data/list-query ds/pmm-db "select * from contact"))

(defn get-contact-by-id
  [key] (data/get-query ds/pmm-db ["select * from contact where id = ?" key]))

(defn get-contact-by-email
  [key] (data/get-query ds/pmm-db ["select * from contact where email = ?" key]))

(defn create-contact
  [entity] (data/create ds/pmm-db :contact (select-keys entity '(:firstname :lastname :email :zipcode))))
