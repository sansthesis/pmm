(ns pmm.views.links
  (:require [rest.common :as common]
            [noir.core :as noir]))

(defn generate-link
  ([vals] (common/remove-null-values vals)))

(defn generate-root-link
  ([] (generate-root-link {}))
  ([{:keys [rel type] :or {rel "root" type "application/json"}}] 
    (generate-link {:rel rel
                    :type type
                    :href (common/full-url-for (noir/url-for pmm.views.root/root))})))

(defn generate-contact-link
  ([{:keys [id rel title type] :or {rel "contact" type "application/json"}}]
    (generate-link {:rel rel
                    :type type
                    :title title
                    :href (common/full-url-for (noir/url-for pmm.views.contacts/get-contact-by-id {:id id}))})))

(defn generate-contacts-link
  ([] (generate-contacts-link {}))
  ([{:keys [rel type title] :or {rel "contacts" type "application/json" title "Contacts"}}]
    (generate-link {:rel rel
                    :type type
                    :title title
                    :href (common/full-url-for (noir/url-for pmm.views.contacts/get-contacts))})))
