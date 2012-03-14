(ns pmm.views.links
  (:require [rest.common :as common]))

(defn generate-root-link
  ([] (generate-root-link "root"))
  ([rel] (common/generate-link :href (common/full-url-for "/") :rel rel :type "application/json")))

(defn generate-customer-link
  ([id] (generate-customer-link id "customer"))
  ([id rel] (generate-customer-link id rel nil))
  ([id rel title] (common/generate-link :href (common/full-url-for (str "/customers/" id)) :rel rel :type "application/json" :title title)))

(defn generate-customers-link
  ([] (generate-customers-link "customers"))
  ([rel] (common/generate-link :href (common/full-url-for "/customers") :rel rel :type "application/json" :title "Customers")))
