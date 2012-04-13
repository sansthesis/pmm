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

(defn list-organizations
  [] (data/list-query ds/pmm-db "select * from organization"))

(defn get-organization-by-id
  [key] (data/get-query ds/pmm-db ["select * from organization where id = ?" key]))

(defn create-organization
  [entity] (data/create ds/pmm-db :organization (select-keys entity '(:name))))

(defn list-divisions-by-organization
  [organization] (data/list-query ds/pmm-db ["select * from division where organization = ?" organization]))

(defn create-division
  [entity] (data/create ds/pmm-db :division (select-keys entity '(:name :number :parent :organization))))

(defn get-division-by-id
  [key] (data/get-query ds/pmm-db ["select * from division where id = ?" key]))
