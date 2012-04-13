(ns pmm.views.links
  (:require [rest.common :as common]))

(defn generate-root-link
  ([] (generate-root-link "root"))
  ([rel] (common/generate-link :href (common/full-url-for "/") :rel rel :type "application/json")))

(defn generate-contact-link
  ([id] (generate-contact-link id "contact"))
  ([id rel] (generate-contact-link id rel nil))
  ([id rel title] (common/generate-link :href (common/full-url-for (str "/contacts/" id)) :rel rel :type "application/json" :title title)))

(defn generate-contacts-link
  ([] (generate-contacts-link "contacts"))
  ([rel] (common/generate-link :href (common/full-url-for "/contacts") :rel rel :type "application/json" :title "contacts")))
