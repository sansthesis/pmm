(ns pmm.dao
  (:require [clojure.java.jdbc :as jdbc]
            [pmm.ds :as ds]
            [praxis.data :as data]))

(defn list-customers
  [] (data/list-query ds/pmm-db "select * from customer"))

(defn get-customer-by-id
  [key] (data/get-query ds/pmm-db ["select * from customer where id = ?" key]))

(defn get-customer-by-email
  [key] (data/get-query ds/pmm-db ["select * from customer where email = ?" key]))

(defn create-customer
  [entity] (data/create ds/pmm-db :customer (select-keys entity '(:firstname :lastname :email :zipcode))))
